package com.anubis.flickr.fragments;

import static com.anubis.flickr.FlickrClientApp.getJacksonService;
import static com.anubis.flickr.R.string.paintings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.anubis.flickr.models.Paintings;
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
import io.realm.RealmList;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class InterestingFragment extends FlickrBaseFragment {
  InterestingAdapter rAdapter;
  RecyclerView rvPhotos;
  List<Photo> photoList = new ArrayList<Photo>();
  //RealmChangeListener changeListener, changeListener2;
  private Realm interestingRealm;
  Interesting mInteresting;
  Paintings mPaintings;
  Subscription interestingSubscription;
  Subscription paintingsSubscription;
  com.aurelhubert.ahbottomnavigation.AHBottomNavigation bottomNavigation;

  @Override
  public void onResume() {
    super.onResume();
    Log.d("TABS", "interesting onresume");
  }

  private void setupBottomNav(com.aurelhubert.ahbottomnavigation.AHBottomNavigation bottomNavigation) {
    // Create items
    AHBottomNavigationItem item1 = new AHBottomNavigationItem(getActivity().getString(R.string.explore), R.drawable.rocket_transparent, fetchColor(R.color.cooler));
    AHBottomNavigationItem item2 = new AHBottomNavigationItem(getActivity().getString(paintings), R.drawable.rocket_transparent, fetchColor(R.color.cooler));
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
        if (position == 0) {
          bottomNavigation.setAccentColor(fetchColor(R.color.delicious));
          getInteresting();
        } else if (position == 1) {
          bottomNavigation.setAccentColor(fetchColor(R.color.cider));
          getPaintings();
        } else {
          bottomNavigation.setAccentColor(fetchColor(R.color.satin));
        }

        // getPhotos(position);
      }
    });
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    rAdapter = new InterestingAdapter(FlickrClientApp.getAppContext(), photoList, true);
   /* changeListener = new RealmChangeListener<Interesting>() {
      @Override
      public void onChange(Interesting i) {
        updateDisplay(i.getInterestingPhotos());
      }
    };
    changeListener2 = new RealmChangeListener<Paintings>() {
      @Override
      public void onChange(Paintings i) {
        updateDisplay(i.getPaintingPhotos());
      }
    };*/
    interestingRealm = Realm.getDefaultInstance();
    getInteresting();
    //@todo getPaintings here, then switch update display in ontabselected
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Log.d("TABS", "interesting activcreated");
  }
//@todo might need for sync adapter updates
  private void redisplayInteresting() {
    if (mInteresting == null) {
      final Date maxDate = interestingRealm.where(Interesting.class).maximumDate("timestamp");
      mInteresting = interestingRealm.where(Interesting.class).equalTo("timestamp", maxDate).findFirst();
    }
    updateDisplay(mInteresting.getInterestingPhotos());
  }
//@todo best practices for opening realms
  private void getInteresting() {
    mInteresting = interestingRealm.where(Interesting.class).findFirst();
    if (mInteresting == null) {
      showProgress("Please wait, loading interesting data...");
      interestingRealm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
          mInteresting = realm.createObject(Interesting.class, Calendar.getInstance().getTime().toString());
          mInteresting.setTimestamp(Calendar.getInstance().getTime());
          realm.insertOrUpdate(mInteresting);
          //mInteresting.addChangeListener(changeListener);
        }
      });
      getInterestingPhotos();
    } else {
      Log.d("INTERESTING", "size:" + mInteresting.getInterestingPhotos().size());
      updateDisplay(mInteresting.getInterestingPhotos());
      //mInteresting.addChangeListener(changeListener);
    }
  }

  private void getPaintings() {
    if (interestingRealm.where(Paintings.class).findFirst() == null) {
      showProgress("Please wait, loading paintings data...");
      interestingRealm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
          mPaintings = realm.createObject(Paintings.class, Calendar.getInstance().getTime().toString());
          mPaintings.setTimestamp(Calendar.getInstance().getTime());
          realm.insertOrUpdate(mPaintings);
          // mPaintings.addChangeListener(changeListener2);
        }
      });
      getPaintingsPhotos();
    } else {
      if (mPaintings == null) {
        mPaintings = interestingRealm.where(Paintings.class).findFirst();
      }
      Log.d("INTERESTING", "size:" + mPaintings.getPaintingPhotos().size());
      updateDisplay(mPaintings.getPaintingPhotos());
    }
  }

  private void updateDisplay(RealmList realmList) {
    Log.d("UPDATE DISPLAY", "list: +" + realmList);
    photoList.clear();
    photoList.addAll(realmList);
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
      ClassifierType classifier = bottomNavigation.getCurrentItem() == 0 ? ClassifierType.CLASSIFIER_INCEPTION : ClassifierType.CLASSIFIER_RETRAINED;
      intent.putExtra(CLASSIFIER_TYPE, classifier.getName());
      intent.putExtra(CLASSIFIER_WIDTH, classifier.getInputSize());
      intent.putExtra(RESULT, photo.getId());
      startActivity(intent);
    });
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (null != interestingSubscription) {
      interestingSubscription.unsubscribe();
    }
    if (null != paintingsSubscription) {
      paintingsSubscription.unsubscribe();
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

  public void getPaintingsPhotos() {
    //@todo offline mode
    //@TODO need iterableFLATMAP TO GET ALL PAGES
    paintingsSubscription = getJacksonService().getPaintingsPhotos()
        .retry()
        .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
        .observeOn(Schedulers.io())
        .subscribe(new Subscriber<Photos>() {
          @Override
          public void onCompleted() {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
              @Override
              public void run() {
                mPaintings = interestingRealm.where(Paintings.class).findFirst();
                updateDisplay(mPaintings.getPaintingPhotos());
                dismissProgress();
              }
            });
          }

          @Override
          public void onError(Throwable e) {
            // cast to retrofit.HttpException to get the response code
            if (e instanceof HttpException) {
              HttpException response = (HttpException) e;
              int code = response.code();
              Log.e("ERROR", String.valueOf(code));
            }
            Log.e("ERROR", "error getting paintings photos" + e);
          }

          @Override
          public void onNext(Photos p) {
            //og.d("DEBUG", "onNext interesting: " + p.getPhotos().getPhotoList());
            Realm realm4 = null;
            try {
              realm4 = Realm.getDefaultInstance();
              realm4.beginTransaction();
              Date maxDate = realm4.where(Paintings.class).maximumDate("timestamp");
              Paintings paintings = realm4.where(Paintings.class).equalTo("timestamp", maxDate).findFirst();
              for (Photo photo : p.getPhotos().getPhotoList()) {
                paintings.paintingPhotos.add(photo);
              }
              paintings.timestamp = paintings.getTimestamp();
              realm4.copyToRealmOrUpdate(paintings);  //deep copy
              realm4.commitTransaction();
              //on main looper
              Log.d("DEBUG", "end get paintings: " + paintings);
            } finally {
              if (null != realm4) {
                realm4.close();
              }
            }
          }
        });
  }

  public void getInterestingPhotos() {
    interestingSubscription = getJacksonService().getInterestingPhotos("1")
        .retry()
        .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
        .observeOn(Schedulers.io())
        .subscribe(new Subscriber<Photos>() {
          @Override
          public void onCompleted() {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
              @Override
              public void run() {
                mInteresting = interestingRealm.where(Interesting.class).findFirst();
                Log.d("INTERESTING oncompleted", "size:" + mInteresting.getInterestingPhotos().size());
                updateDisplay(mInteresting.getInterestingPhotos());
                dismissProgress();
              }
            });
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



