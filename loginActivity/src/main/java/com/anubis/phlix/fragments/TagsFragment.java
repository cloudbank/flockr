package com.anubis.phlix.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anubis.phlix.FlickrClientApp;
import com.anubis.phlix.R;
import com.anubis.phlix.activity.ImageDisplayActivity;
import com.anubis.phlix.adapter.TagsAdapter;
import com.anubis.phlix.models.Photo;
import com.anubis.phlix.models.Photos;
import com.anubis.phlix.models.Recent;
import com.anubis.phlix.models.Tag;
import com.anubis.phlix.models.TagAndRecent;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.hkm.soltag.TagContainerLayout;
import co.hkm.soltag.TagView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static com.anubis.phlix.FlickrClientApp.getJacksonService;


public class TagsFragment extends FlickrBaseFragment {

    List<Tag> mTags = new ArrayList<Tag>();
    TagContainerLayout mTagsView;
    private List<Photo> mPhotos = new ArrayList<Photo>();
    Subscription recentSubscription;
    AdView mPublisherAdView;
    TagsAdapter tAdapter;
    RecyclerView rvPhotos;
    Recent mRecent;

    RealmChangeListener changeListener;
    Realm tagsRealm, r;


    @Override
    public void onPause() {
        if (mPublisherAdView != null) {
            mPublisherAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mPublisherAdView != null) {
            mPublisherAdView.resume();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        changeListener = new RealmChangeListener<Recent>() {
            @Override
            public void onChange(Recent r) {

                updateDisplay(r);
            }
        };


        tagsRealm = Realm.getDefaultInstance();
        final Date maxDate = tagsRealm.where(Recent.class).maximumDate("timestamp");
        //@todo get the last selected color?
        mRecent = tagsRealm.where(Recent.class).equalTo("timestamp", maxDate).findFirst();
        if (mRecent == null) {
            showProgress("Please wait, loading interesting data...");
            tagsRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    bgRealm.createObject(Recent.class, Calendar.getInstance().getTime().toString());


                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    //change listeners only on looper threads
                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(() -> {
                        mRecent = tagsRealm.where(Recent.class).equalTo("timestamp", maxDate).findFirst();


                        mRecent.addChangeListener(changeListener);
                        getRecentAndHotags();
                    });

                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    // Transaction failed and was automatically canceled.
                }
            });


        } else {
            mRecent.addChangeListener(changeListener);
            updateDisplay(mRecent);
        }

    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tAdapter = new TagsAdapter(getActivity(), mPhotos, false);

        Log.d("TABS", "tags oncreate");
        setRetainInstance(true);
    }


    private void updateDisplay(Recent r) {

        displayHotTags(r.getHotTagList());
        mPhotos.clear();
        if (r != null) {
            mPhotos.addAll(r.getRecentPhotos());
        }

        tAdapter.notifyDataSetChanged();


    }


    public void displayHotTags(List<Tag> tags) {
        //tags.stream().map(it -> it.getContent()).collect(Collectors.toCollection())
        if (null != mTagsView) {
            mTagsView.removeAllTags();
            for (Tag t : tags) {
                mTagsView.addTag(t.getContent());
            }
         }

    }


    @Override
    public void onDestroy() {
        if (mPublisherAdView != null) {
            mPublisherAdView.pause();
        }
        super.onDestroy();
        if (null != r && !r.isClosed()) {
            r.close();
        }
        if (null != tagsRealm && !tagsRealm.isClosed()) {
            tagsRealm.close();
        }
        if (null != mRecent) {
            mRecent.removeChangeListeners();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tags, container,
                false);
        mTagsView = (TagContainerLayout) view.findViewById(R.id.tag_group);
        mTagsView.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                //Toast.makeText(FlickrClientApp.getAppContext(), "Tag " + text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTagLongClick(final int position, String text) {
            }
        });
        rvPhotos = (RecyclerView) view.findViewById(R.id.rvPhotos);
        rvPhotos.setAdapter(tAdapter);
        rvPhotos.setLayoutManager(new GridLayoutManager(FlickrClientApp.getAppContext(), 3));
        tAdapter.setOnItemClickListener((view1, position) -> {
            Intent intent = new Intent(getActivity(),
                    ImageDisplayActivity.class);
            Photo photo = mPhotos.get(position);
            intent.putExtra(RESULT, photo.getId());
            startActivity(intent);
        });

        mPublisherAdView = (AdView) view.findViewById(R.id.publisherAdView);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                //.addTestDevice("9D3A392231B42400A9CCA1CBED2D006F")  // My Galaxy Nexus test phone
                .build();
        mPublisherAdView.loadAd(adRequest);
        setHasOptionsMenu(true);
        return view;
    }

    private void getRecentAndHotags() {
        Observable<Photos> recentObservable = getJacksonService().getRecentPhotos();
        recentSubscription = getJacksonService().getHotTags().zipWith(recentObservable, (h, p) -> {
            return new TagAndRecent(p, h);
        }).subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<TagAndRecent>() {
                    @Override
                    public void onCompleted() {


                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(() -> dismissProgress());

                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                            Log.e("ERROR", String.valueOf(code));
                        }
                        Log.e("ERROR", "error getting tags/photos" + e);
                    }

                    @Override
                    public void onNext(TagAndRecent t) {
                        Realm realm4 = null;
                        try {
                            realm4 = Realm.getDefaultInstance();
                            realm4.beginTransaction();

                            Date maxDate = realm4.where(Recent.class).maximumDate("timestamp");
                            Recent recent = realm4.where(Recent.class).equalTo("timestamp", maxDate).findFirst();

                            for (Photo p : t.getRecent().getPhotos().getPhotoList()) {
                                recent.recentPhotos.add(p);
                                //set not interesting @todo
                            }
                            recent.timestamp = maxDate;


                            for (Tag tag : t.getHottags().getHottags().getTag()) {
                                recent.hotTagList.add(tag);
                            }
                            realm4.copyToRealmOrUpdate(recent);  //deep copy

                            realm4.commitTransaction();
                            Log.d("DEBUG", "end recent/tag");
                        } finally {
                            if (null != realm4) {
                                realm4.close();
                            }
                        }
                    }

                });

    }

}
