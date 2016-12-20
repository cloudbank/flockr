package com.anubis.phlix.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.anubis.phlix.FlickrClientApp;
import com.anubis.phlix.R;
import com.anubis.phlix.fragments.FlickrBaseFragment;
import com.anubis.phlix.models.Comment;
import com.anubis.phlix.models.Comments;
import com.anubis.phlix.models.Comments_;
import com.anubis.phlix.models.Photo;
import com.anubis.phlix.util.DateUtility;
import com.anubis.phlix.util.ImageRoundedTransformation;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.hkm.soltag.TagContainerLayout;
import io.realm.Realm;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.anubis.phlix.R.id.comments;

public class ImageDisplayActivity extends AppCompatActivity {

    WebView wvComments;
    TagContainerLayout mTags;
    EditText etComments;
    String mUid = "";
    String mContent;
    StringBuilder mBuilder;
    private Subscription subscription, subscription2;
    Map<String, String> data = new HashMap<>();
    Photo mPhoto;
    Realm pRealm;
    HandlerThread handlerThread;
    AdView mPublisherAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_rocket);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.image_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String pid = getIntent().getStringExtra(FlickrBaseFragment.RESULT);
        pRealm = Realm.getDefaultInstance();
        mPhoto = pRealm.where(Photo.class).equalTo("id", pid).findFirst();

        wvComments = (WebView) findViewById(comments);
        wvComments.setBackgroundColor(getResources().getColor(R.color.AliceBlue));
        wvComments.setVerticalScrollBarEnabled(true);
        wvComments.setHorizontalScrollBarEnabled(true);
        wvComments.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wvComments.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        wvComments.getSettings().setJavaScriptEnabled(true);

        mUid = mPhoto.getId();
        //@todo comments do not get refreshed w sync adapter
        //@todo this needs to check if 24 hr has passed
        Comments_ c = pRealm.where(Comments_.class).equalTo("photoId", mUid).findFirst();
        if (null == c || (null != c && !withinADay(c.getTimestamp()))) {
            //network call
            getComments(mUid);
        } else {
            displayComments(wvComments, c.commentsList);
        }


        ImageView imageView = (ImageView) findViewById(R.id.ivResult);
        Picasso.with(getBaseContext()).load(mPhoto.getUrl()).transform(new ImageRoundedTransformation(5, 5)).resize(300, 300).centerCrop().into(imageView);

        TextView tvUsername = (TextView) findViewById(R.id.tvUser);
        tvUsername.setText(mPhoto.getOwnername());
        TextView tvTimestamp = (TextView) findViewById(R.id.timestamp);
        //@todo relative Time pretty time
        tvTimestamp.setText(DateUtility.relativeTime(mPhoto.getDatetaken(), this));

        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(mPhoto.getTitle());

        etComments = (EditText) findViewById(R.id.etComments);
        etComments.setScroller(new Scroller(this));
        etComments.setMaxLines(1);
        etComments.setVerticalScrollBarEnabled(true);
        etComments.setMovementMethod(new ScrollingMovementMethod());



        mTags = (TagContainerLayout) findViewById(R.id.tag_group);
        displayTags(mPhoto.getTags());
        mPublisherAdView = (AdView) findViewById(R.id.publisherAdView);
        AdRequest adRequest = new AdRequest.Builder()
               // .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
               // .addTestDevice("9D3A392231B42400A9CCA1CBED2D006F")  // My Galaxy Nexus test phone
                .build();
        mPublisherAdView.loadAd(adRequest);


}

    private boolean withinADay(Date timestamp) {
        long diff = (new Date().getTime() - timestamp.getTime());
        if (diff > 86400000) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void getComments(final String uid) {
        //Observable<PhotoInfo> photoInfo = FlickrClientApp.getJacksonService().getPhotoInfo(uid);
        subscription = FlickrClientApp.getJacksonService().getComments(uid)

                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Comments>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                            Log.e("ERROR", String.valueOf(code));
                        }
                        Log.e("ERROR", "error getting comments" + e);
                    }

                    @Override
                    public void onNext(Comments c) {
                        Log.d("DEBUG", "comments: " + comments);
                        List<Comment> comments = c.getComments().getComments();
                        if (comments.size() > 0) {
                            if (saveComments(comments, mUid)) {
                                displayComments(wvComments, comments);
                            } else {
                                Log.e("ERROR", "comments not saved: " + c);
                            }
                        }
                    }
                });

    }

    public void displayTags(String tags) {
        //tags.stream().map(it -> it.getContent()).collect(Collectors.toCollection())
        if (tags.length() == 0) {
            mTags.setVisibility(View.GONE);
        } else {
            mTags.setVisibility(View.VISIBLE);
        }
        for (String s : tags.split(" ")) {
            mTags.addTag(s);
        }


    }



    public boolean saveComments(final List<Comment> cList, final String uid) {
        Log.d("SAVE COMMENT", String.valueOf(cList.get(0).getContent()) + ":" + uid);
        handlerThread = new HandlerThread("BackgroundHandler");
        handlerThread.start();
        final Handler backgroundHandler = new Handler(handlerThread.getLooper());
        return backgroundHandler.post(() -> {
            Realm realm = null;
            try {
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                Comments_ c = realm.where(Comments_.class).equalTo("photoId", uid).findFirst();
                if (null == c) {
                    c = realm.createObject(Comments_.class, uid);
                }

                for (Comment comment : cList) {
                    if (!c.commentsList.contains(comment)) {
                        c.commentsList.add(comment);
                    }
                }
                c.setTimestamp(new Date());
                realm.copyToRealmOrUpdate(c);
                realm.commitTransaction();
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }
        });


    }


    //@todo check for idempotence only once put, and in reverse w date
    public void addComment(View v) {
        String commentString = etComments.getText().toString();
        etComments.setText("");
        etComments.clearFocus();
        if (commentString.length() > 0) {

            data.put("comment_text", commentString);
            data.put("photo_id", mPhoto.getId());
            subscription2 = FlickrClientApp.getJacksonService().addComment(data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Comment>() {
                        @Override
                        public void onCompleted() {


                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                HttpException response = (HttpException) e;
                                int code = response.code();
                                Log.e("ERROR", String.valueOf(code));
                            }
                            Log.e("ERROR", "error add comment" + e);
                        }

                        @Override
                        public void onNext(Comment c) {
                            //@todo maybe we should let this be a network call to have fresh data?
                            List<Comment> displayList = new ArrayList<Comment>();
                            Realm cRealm = Realm.getDefaultInstance();
                            Comments_ comments = cRealm.where(Comments_.class).equalTo("photoId", mUid).findFirst();
                            if (null != comments && comments.getCommentsList().size() > 0) {
                                displayList.addAll(comments.getCommentsList());
                            }


                            List<Comment> commentsList = new ArrayList<Comment>();
                            Comment comment = new Comment();
                            //@todo  workaround the json maps anon obj { comment: {
                            Map comObj = (Map) c.getAdditionalProperties().get("comment");
                            comment.setAuthor((String) comObj.get("author"));
                            comment.setId((String) comObj.get("id"));
                            comment.setAuthorname((String) comObj.get("authorname"));
                            comment.setDatecreate((String) comObj.get("datecreate"));
                            comment.setContent((String) comObj.get("_content"));
                            commentsList.add(comment);
                            if (saveComments(commentsList, mUid)) {
                                displayList.add(comment);
                                displayComments(wvComments, displayList);
                            } else {
                                //@todo comment not added
                                Log.e("ERROR", "comment not added: " + c);
                            }
                            cRealm.close();
                        }
                    });

        }
    }


    //
    private void displayComments(WebView commentsView, List<Comment> comments) {
        mBuilder = new StringBuilder();
        mBuilder.append("<html><head>  <style> body {color: #4169E1;  font-size: 12px;}</style></head><br>");
        for (int i = comments.size() - 1; i >= 0; i--) {
            Comment c = comments.get(i);
            if (null != c && null != c.getContent()) {
                mContent = c.getContent();
                String htmlString = mContent;

                if (mContent.contains("[http") && !mContent.contains("src")) {
                    htmlString = mContent.replaceAll("\\[(\\s*http\\S+\\s*)\\]", "<a href=\"" + "$1" + "\">$1</a><br>");
                }
                String time = new PrettyTime().format(new Date(Long.parseLong(c.getDatecreate()) * 1000L));
                mBuilder.append("<b>");
                mBuilder.append(c.getAuthorname());
                mBuilder.append(":</b> (");
                mBuilder.append(time);
                mBuilder.append(")<br>");
                mBuilder.append(htmlString);
                mBuilder.append("<br><br>");
            }
        }

        mBuilder.append("</body></html>");
        commentsView.loadUrl("about:blank");
        commentsView.loadData(mBuilder.toString(), "text/html", "utf-8");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != pRealm && !pRealm.isClosed()) {
            pRealm.close();
        }
        if (null != subscription) {
            subscription.unsubscribe();
        }
        if (null != subscription2) {
            subscription2.unsubscribe();
        }
        if (null != handlerThread) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                if (null != handlerThread.getLooper()) {
                    handlerThread.getLooper().quitSafely();
                }
            } else {
                handlerThread.quit();
            }
        }

    }

}
