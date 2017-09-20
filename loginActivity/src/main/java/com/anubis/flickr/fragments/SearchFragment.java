package com.anubis.flickr.fragments;

import static com.anubis.flickr.FlickrClientApp.getJacksonService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anubis.flickr.FlickrClientApp;
import com.anubis.flickr.R;
import com.anubis.flickr.activity.ImageDisplayActivity;
import com.anubis.flickr.adapter.SearchAdapter;
import com.anubis.flickr.adapter.SpacesItemDecoration;
import com.anubis.flickr.models.Paintings;
import com.anubis.flickr.models.Photo;
import com.anubis.flickr.models.Photos;

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

public class SearchFragment extends FlickrBaseFragment {
  RecyclerView rvPhotos;
  SearchAdapter searchAdapter;
  List<Photo> sPhotos = new ArrayList<Photo>();
  Realm commonsRealm;
  RealmChangeListener changeListener;
  Subscription commonSubscription;
  Paintings mCommon;

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (null != commonSubscription) {
      commonSubscription.unsubscribe();
    }
    if (null != commonsRealm && !commonsRealm.isClosed()) {
      commonsRealm.close();
    }
    if (null != mCommon) {
      mCommon.removeChangeListeners();
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    searchAdapter = new SearchAdapter(FlickrClientApp.getAppContext(), sPhotos, true);
    changeListener = new RealmChangeListener<Paintings>() {
      @Override
      public void onChange(Paintings c) {
        updateDisplay(c);
      }
    };
    commonsRealm = Realm.getDefaultInstance();
    final Date maxDate = commonsRealm.where(Paintings.class).maximumDate("timestamp");
    mCommon = commonsRealm.where(Paintings.class).equalTo("timestamp", maxDate).findFirst();
    if (mCommon == null) {
      showProgress("Loading data, please wait...");
      commonsRealm.executeTransactionAsync(new Realm.Transaction() {
        @Override
        public void execute(Realm bgRealm) {
          bgRealm.createObject(Paintings.class, Calendar.getInstance().getTime().toString());
        }
      }, new Realm.Transaction.OnSuccess() {
        @Override
        public void onSuccess() {
          //change listeners only on looper threads
          Handler handler = new Handler();
          handler.post(() -> {
            mCommon = commonsRealm.where(Paintings.class).equalTo("timestamp", maxDate).findFirst();
            mCommon.addChangeListener(changeListener);
            getCommonsPage1();  //<---- change
          });
        }
      }, new Realm.Transaction.OnError() {
        @Override
        public void onError(Throwable error) {
          // Transaction failed and was automatically canceled.
        }
      });
    } else {
      //<--sync adapter
      mCommon.addChangeListener(changeListener);
      updateDisplay(mCommon);
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    Log.d("TABS", "search onresume");
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("TABS", "search oncreate");
    setRetainInstance(true);
  }

  private void updateDisplay(Paintings c) {
    Log.d("TABS", "search updateDisplay(s)");
    sPhotos.clear();
    if (null != c) {
      sPhotos.addAll(c.getPaintingPhotos());
    }
    searchAdapter.notifyDataSetChanged();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search, container, false);
    rvPhotos = (RecyclerView) view.findViewById(R.id.rvSearch);
    rvPhotos.setAdapter(searchAdapter);
    StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    rvPhotos.setLayoutManager(gridLayoutManager);
    SpacesItemDecoration decoration = new SpacesItemDecoration(15);
    rvPhotos.addItemDecoration(decoration);
    searchAdapter.setOnItemClickListener((view1, position) -> {
      Intent intent = new Intent(getActivity(),
          ImageDisplayActivity.class);
      Photo photo = sPhotos.get(position);
      intent.putExtra(RESULT, photo.getId());
      startActivity(intent);
    });
    Log.d("TABS", "search oncreateview");
    setHasOptionsMenu(true);
    return view;
  }

  private void getCommonsPage1() {
    //@todo check for page total if not then process with page 1
    //@todo while realm total is less than total increment page else stop
    commonSubscription = getJacksonService().commons("1")
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
            Log.e("ERROR", "error getting commons1/photos" + e);
          }

          @Override
          public void onNext(Photos p) {
            Realm realm = null;
            try {
              realm = Realm.getDefaultInstance();
              realm.beginTransaction();
              Date maxDate = realm.where(Paintings.class).maximumDate("timestamp");
              Paintings c = realm.where(Paintings.class).equalTo("timestamp", maxDate).findFirst();
              for (Photo photo : p.getPhotos().getPhotoList()) {
                photo.isCommon = true;
                c.paintingPhotos.add(photo);
              }
              c.timestamp = Calendar.getInstance().getTime();
              realm.copyToRealmOrUpdate(c);
              realm.commitTransaction();
            } finally {
              if (null != realm) {
                realm.close();
              }
            }
          }
        });
  }
}


