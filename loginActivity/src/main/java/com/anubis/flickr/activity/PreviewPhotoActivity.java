package com.anubis.flickr.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.anubis.flickr.FlickrClientApp;
import com.anubis.flickr.R;
import com.anubis.flickr.fragments.FlickrBaseFragment;
import com.anubis.flickr.models.Photo;
import com.anubis.flickr.models.Photos;
import com.anubis.flickr.models.UserModel;
import com.anubis.flickr.util.ImageFilterProcessor;
import com.anubis.flickr.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PreviewPhotoActivity extends AppCompatActivity {
    public static final String IMAGE_PNG = "image.png";
    public static final String PLEASE_WAIT = "Please wait";
    public static final String UPLOADING_IMAGE = "Uploading Image";
    public static final String PHOTOID_BEGINTAG = "<photoid>";
    public static final String PHOTOID_ENDTAG = "</photoid>";
    String filename;
    private Bitmap photoBitmap;
    private Bitmap processedBitmap;
    private EditText etFilename;
    private ImageView ivPreview;
    private ImageFilterProcessor filterProcessor;
    private Subscription subscription;
    ProgressDialog ringProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_photo);
        ivPreview = (ImageView) findViewById(R.id.ivPreview);
        etFilename = (EditText) findViewById(R.id.etPhotoName);
        photoBitmap = getIntent().getParcelableExtra(FlickrBaseFragment.PHOTO_BITMAP);
        filterProcessor = new ImageFilterProcessor(photoBitmap);
        redisplayPreview(ImageFilterProcessor.NONE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle("Picture Preview");
        //overdraw fix
        getWindow().setBackgroundDrawable(null);

    }

    private void redisplayPreview(int effectId) {
        processedBitmap = filterProcessor.applyFilter(effectId);
        ivPreview.setImageBitmap(processedBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preview_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.more || itemId == R.id.action_save)
            return true;

        int effectId = 0;

        if (itemId == R.id.filter_none) {
            effectId = ImageFilterProcessor.NONE;
        } else if (itemId == R.id.filter_blur) {
            effectId = ImageFilterProcessor.BLUR;
        } else if (itemId == R.id.filter_grayscale) {
            effectId = ImageFilterProcessor.GRAYSCALE;
        } else if (itemId == R.id.filter_crystallize) {
            effectId = ImageFilterProcessor.CRYSTALLIZE;
        } else if (itemId == R.id.filter_solarize) {
            effectId = ImageFilterProcessor.SOLARIZE;
        } else if (itemId == R.id.filter_glow) {
            effectId = ImageFilterProcessor.GLOW;
        } else {
            effectId = ImageFilterProcessor.NONE;
        }
        redisplayPreview(effectId);
        return true;
    }

    public void onCancelButton(MenuItem menuItem) {
        this.finish();


    }

    public void onSaveButton(MenuItem menuItem) {
        ringProgressDialog = new ProgressDialog(this, R.style.CustomProgessBarStyle);
        ringProgressDialog.setTitle(PLEASE_WAIT);
        ringProgressDialog.setMessage(UPLOADING_IMAGE);

        ringProgressDialog.setCancelable(true);
        ringProgressDialog.show();
        filename = etFilename.getText().toString();
        if (filename.length() == 0) {
            filename = IMAGE_PNG;
        }


//@todo can put bitmap into realm or create its url
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        processedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        final byte[] bytes = stream.toByteArray();


        MultipartBody.Part filePart = MultipartBody.Part.createFormData("photo", filename, RequestBody.create(MediaType.parse("image/*"), bytes));

        subscription = FlickrClientApp.getDefaultService().postPhoto(filePart)
                .concatMap(new Func1<ResponseBody, Observable<Photos>>() {
                    @Override
                    public Observable<Photos> call(ResponseBody res) {
                        try {
                            Intent data = new Intent();
                            String resp = res.string();
                            Log.d("DEBUG", "post photo: " + resp);
                            String photoId = parseId(resp);
                            data.putExtra("photoId", photoId);
                            setResult(RESULT_OK, data);


                        } catch (IOException | ArrayIndexOutOfBoundsException e) {
                            Log.e("ERROR", "exception while posting photo");
                        } finally {

                        }
                        return FlickrClientApp.getJacksonService().getFriendsPhotos(Util.getUserId());
                    }
                })
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Photos>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                            Log.e("ERROR", String.valueOf(code));
                        }
                        Log.e("ERROR", "error posting photo" + e);
                    }

                    @Override
                    public void onNext(Photos photos) {
                        //@todo do I want to return the boolean here
                        saveFriends(photos);

                        ringProgressDialog.dismiss();

                        PreviewPhotoActivity.this.finish();


                    }

                });


    }

    private void saveFriends(final Photos photos) {
        HandlerThread handlerThread = new HandlerThread("BackgroundHandler");
        handlerThread.start();
        final Handler backgroundHandler = new Handler(handlerThread.getLooper());
        backgroundHandler.post(new Runnable() {

            @Override
            public void run() {
                Realm realm = null;
                try {
                    realm = Realm.getDefaultInstance();
                    realm.beginTransaction();


                    UserModel u = realm.where(UserModel.class).equalTo("userId", Util.getUserId()).findFirst();
                    if (u.friendsList.size() > 0) {
                        u.friendsList = null;
                    }
                    for (Photo p : photos.getPhotos().getPhotoList()) {
                        u.friendsList.add(p);
                    }
                    u.name = Util.getCurrentUser();
                    u.timestamp = Calendar.getInstance().getTime();
                    u.tagsList = u.getTagsList();
                    realm.copyToRealmOrUpdate(u);  //deep copy
                    realm.commitTransaction();
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != subscription) {
            subscription.unsubscribe();
        }
        if (null != ringProgressDialog) {
            ringProgressDialog = null;
        }
    }

    private String parseId(String xmlString) {
        return xmlString.substring((xmlString.indexOf(PHOTOID_BEGINTAG)) + 9,
                xmlString.indexOf(PHOTOID_ENDTAG));
    }

}




