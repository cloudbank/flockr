package com.anubis.flickr.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anubis.flickr.FlickrClientApp;
import com.anubis.flickr.R;
import com.anubis.flickr.activity.ClassifierDisplayActivity;
import com.anubis.flickr.adapter.InterestingAdapter;
import com.anubis.flickr.models.Interesting;
import com.anubis.flickr.models.Photo;
import com.anubis.flickr.models.Photos;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import org.tensorflow.tensorlib.classifier.ClassifierType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static com.anubis.flickr.FlickrClientApp.getJacksonService;

public class InterestingFragment extends FlickrBaseFragment {
    InterestingAdapter rAdapter;
    RecyclerView rvPhotos;
    List<Photo> photoList = new ArrayList<Photo>();
    RealmChangeListener changeListener;
    Realm interestingRealm, r;
    Interesting mInteresting;
    Subscription interestingSubscription;
    com.aurelhubert.ahbottomnavigation.AHBottomNavigation bottomNavigation;
    HandlerThread handlerThread;
    @Override
    public void onResume() {
        super.onResume();
        Log.d("TABS", "interesting onresume");
    }
    private void setupBottomNav(com.aurelhubert.ahbottomnavigation.AHBottomNavigation bottomNavigation) {
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(getActivity().getString(R.string.explore), R.drawable.rocket_transparent, fetchColor(R.color.cooler));

        AHBottomNavigationItem item2 = new AHBottomNavigationItem(getActivity().getString(R.string.moon), R.drawable.rocket_transparent, fetchColor(R.color.cooler));
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(getActivity().getString(R.string.japan), R.drawable.rocket_transparent, fetchColor(R.color.cooler));

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(fetchColor(R.color.soap));

        // Change colors
        bottomNavigation.setAccentColor(fetchColor(R.color.delicious));
        bottomNavigation.setInactiveColor(fetchColor(android.R.color.darker_gray));


        // Use colored navigation with circle reveal effect
        //bottomNavigation.setColored(true);
        bottomNavigation.setBehaviorTranslationEnabled(true);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);


        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if (position == 1) {
                    bottomNavigation.setAccentColor(fetchColor(R.color.delicious));

                } else if (position == 2) {
                    bottomNavigation.setAccentColor(fetchColor(R.color.delicious));
                } else {
                    bottomNavigation.setAccentColor(fetchColor(R.color.delicious));
                }
               // getPhotos(position);
                //return true;
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        rAdapter = new InterestingAdapter(FlickrClientApp.getAppContext(), photoList, true);

        changeListener = new RealmChangeListener<Interesting>() {
            @Override
            public void onChange(Interesting i) {
                updateDisplay(i);
            }
        };


        interestingRealm = Realm.getDefaultInstance();
        final Date maxDate = interestingRealm.where(Interesting.class).maximumDate("timestamp");
        //@todo get the last selected color?
        mInteresting = interestingRealm.where(Interesting.class).equalTo("timestamp", maxDate).findFirst();
        if (mInteresting == null) {
            showProgress("Please wait, loading interesting data...");
            interestingRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    mInteresting = interestingRealm.createObject(Interesting.class, Calendar.getInstance().getTime().toString());
                    realm.insertOrUpdate(mInteresting);
                    mInteresting.addChangeListener(changeListener);
                }
            });

            getInterestingPhotos();
        } else {
            mInteresting.addChangeListener(changeListener);
            updateDisplay(mInteresting);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TABS", "interesting activcreated");


    }


    private void updateDisplay(Interesting i) {
        photoList.clear();
        photoList.addAll(i.getInterestingPhotos());
        rAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d("TABS", "interesting oncreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interesting, container,
                false);
        rvPhotos = (RecyclerView) view.findViewById(R.id.rvPhotos);
        rvPhotos.setAdapter(rAdapter);
        bottomNavigation = (AHBottomNavigation) view.findViewById(R.id.bottom_navigation);
        setupBottomNav(bottomNavigation);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rvPhotos.setLayoutManager(gridLayoutManager);
        /*rvPhotos.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });*/
        rAdapter.setOnItemClickListener((view1, position) -> {
            Intent intent = new Intent(getActivity(), ClassifierDisplayActivity.class);
            Photo photo = photoList.get(position);
            intent.putExtra(CLASSIFIER_TYPE, ClassifierType.CLASSIFIER_INCEPTION.getName());
            intent.putExtra(RESULT, photo.getId());
            startActivity(intent);
        });
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        handlerThread.quitSafely();
        if (null != r && !r.isClosed()) {
            r.close();
        }
        if (mInteresting != null) {
            mInteresting.removeChangeListeners();
        }
        if (null != interestingRealm && !interestingRealm.isClosed()) {
            interestingRealm.close();
        }
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(getContext(), color);
    }

    public void getInterestingPhotos() {
        //@todo offline mode
        //@TODO need iterableFLATMAP TO GET ALL PAGES
        interestingSubscription = getJacksonService().getInterestingPhotos("1")
                .retry()
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Photos>() {
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
                        Log.e("ERROR", "error getting interesting photos" + e);
                    }

                    @Override
                    public void onNext(Photos p) {
                        //og.d("DEBUG", "onNext interesting: " + p.getPhotos().getPhotoList());
                        //pass photos to fragment
                        Realm realm3 = null;
                        try {
                            realm3 = Realm.getDefaultInstance();
                            realm3.beginTransaction();

                            Date maxDate = realm3.where(Interesting.class).maximumDate("timestamp");
                            Interesting interesting = realm3.where(Interesting.class).equalTo("timestamp", maxDate).findFirst();


                            for (Photo photo : p.getPhotos().getPhotoList()) {
                                photo.isInteresting = true;
                                interesting.interestingPhotos.add(photo);

                            }

                            interesting.timestamp = interesting.getTimestamp();
                            realm3.copyToRealmOrUpdate(interesting);  //deep copy
                            realm3.commitTransaction();
                            Log.d("DEBUG", "end get interesting: " + interesting);
                        } finally {
                            if (null != realm3) {
                                realm3.close();
                            }
                        }

                    }
                });

    }


}



