package com.anubis.phlix.fragments;

import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.anubis.phlix.FlickrClientApp;
import com.anubis.phlix.R;
import com.anubis.phlix.activity.ImageDisplayActivity;
import com.anubis.phlix.adapter.FriendsAdapter;
import com.anubis.phlix.models.Photo;
import com.anubis.phlix.models.Photos;
import com.anubis.phlix.models.Tag;
import com.anubis.phlix.models.UserInfo;
import com.anubis.phlix.models.UserModel;
import com.anubis.phlix.models.Who;
import com.anubis.phlix.util.Util;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.hkm.soltag.TagContainerLayout;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static com.anubis.phlix.FlickrClientApp.getJacksonService;

public class FriendsFragment extends FlickrBaseFragment {

    private String mUserId;


    AdView mPublisherAdView;
    FriendsAdapter fAdapter;
    RecyclerView rvPhotos;
    List<Photo> mPhotos = new ArrayList<Photo>();
    List<Photo>cPhotos = new ArrayList<Photo>();
    List<Tag> mTags = new ArrayList<Tag>();
    TagContainerLayout mTagView;
    Realm userRealm, r;
    UserModel mUser;
    RealmChangeListener changeListener;
    RadioGroup rg;
    RadioButton rb1, rb5;
    View view;
    Subscription friendSubscription;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        fAdapter = new FriendsAdapter(getActivity(), mPhotos, false);
        changeListener = new RealmChangeListener<UserModel>() {

            @Override
            public void onChange(UserModel u) {

                updateDisplay(u);
                if (rb1.isChecked()) {
                    makeSingle(cPhotos);
                    fAdapter.notifyDataSetChanged();
                }
            }
        };


        userRealm = Realm.getDefaultInstance();
        String user_id = Util.getUserId();


        mUser = userRealm.where(UserModel.class).equalTo("userId", user_id).findFirst();
        if (mUser == null) {
            showProgress("Loading data, please wait...");

            userRealm.beginTransaction();
            mUser  = userRealm.createObject(UserModel.class, user_id);
            userRealm.commitTransaction();
            mUser.addChangeListener(changeListener);
            getFriends();  //<---- change
        } else {
            //<--sync adapter
            mUser.addChangeListener(changeListener);
            updateDisplay(mUser);
        }



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //new RadioGroup.OnCheckedChangeListener() onCheckedChanged
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio1) {
                makeSingle(cPhotos);
                fAdapter.notifyDataSetChanged();

            } else if (checkedId == R.id.radio5) {
                updateDisplay(mUser);
            }

        });

    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setRetainInstance(true);
    }




    private void updateDisplay(UserModel u) {
        mPhotos.clear();
        cPhotos.clear();

        displayTags(u.getTagsList());
        if (null !=  u) {

            cPhotos.addAll(u.getFriendsList());
            mPhotos.addAll(u.getFriendsList());
        }
        fAdapter.notifyDataSetChanged();
    }




    public void displayTags(List<Tag> tags) {
        //tags.stream().map(it -> it.getContent()).collect(Collectors.toCollection())
        //when android catches up to 1.8
        if (null != mTagView) {
            mTagView.removeAllTags();
        }
        for (Tag t : tags) {
            mTagView.addTag(t.getContent());
        }
    }


    @Override
    public void onDestroy() {
        if (mPublisherAdView != null) {
            mPublisherAdView.destroy();
        }
        super.onDestroy();
        if (null != userRealm && !userRealm.isClosed()) {
            userRealm.close();
        }
        if (null != r && !r.isClosed()) {
            r.close();
        }


    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friends, container,
                false);

        rvPhotos = (RecyclerView) view.findViewById(R.id.rvPhotos);
        rvPhotos.setAdapter(fAdapter);
        rvPhotos.setLayoutManager(new GridLayoutManager(FlickrClientApp.getAppContext(), 3));
        fAdapter.setOnItemClickListener((view1, position) -> {
            Intent intent = new Intent(getActivity(),
                    ImageDisplayActivity.class);
            Photo photo = mPhotos.get(position);
            intent.putExtra(RESULT, photo.getId());
            startActivity(intent);
        });
        mTagView = (TagContainerLayout) view.findViewById(R.id.my_tag_group);
        rg = (RadioGroup) view.findViewById(R.id.radioGroup1);
        rb1 = (RadioButton) view.findViewById(R.id.radio1);
        rb5 = (RadioButton) view.findViewById(R.id.radio5);

        mPublisherAdView = (AdView) view.findViewById(R.id.publisherAdView);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                //.addTestDevice("9D3A392231B42400A9CCA1CBED2D006F")  // My Galaxy Nexus test phone
                .build();
        mPublisherAdView.loadAd(adRequest);
        setHasOptionsMenu(true);

        return view;
    }


    private void makeSingle(List<Photo> p) {
        mPhotos.clear();
        String current = "";
        for (int i = 0; i < p.size(); i++) {
            if (!current.equals(p.get(i).getOwnername())) {
                mPhotos.add(p.get(i));
                current = p.get(i).getOwnername();
            }
        }


    }

    private void getFriends() {
        //new Func2<Photos, Who, UserInfo>()
        //public UserInfo call(Photos p, Who w)  {   return new UserInfo(w, p);

        Observable<Who> tagsObservable = getJacksonService().getTags(Util.getUserId());
        friendSubscription = getJacksonService().getFriendsPhotos(Util.getUserId())
                .zipWith(tagsObservable, (p, w) -> {
                    return new UserInfo(w, p);
                })

                .subscribeOn(Schedulers.io()) // thread pool; bg + bg
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<UserInfo>() {
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
                        Log.e("ERROR", "error getting friends" + e);
                        //signout
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {

                        //add photos to real

                        //if (username.length() == 0) {
                        //throw new Exception("username is not set")
                        //stop the sync adapter and remove account
                        //try to sign out gracefully

                        Photos photos = userInfo.getFriends();
                        Who w = userInfo.getWho();
                        List<Tag> tags = w.getWho().getTags().getTag();
                        // }
                        Realm realm2 = null;
                        try {
                            realm2 = Realm.getDefaultInstance();
                            realm2.beginTransaction();


                            String user_id = Util.getUserId();

                            UserModel u = null;
                            u = realm2.where(UserModel.class).equalTo("userId", user_id).findFirst();


                           /* Date d = Calendar.getInstance().getTime();
                            Interesting i = realm2.createObject(Interesting.class, d.toString());
                            i.setTimestamp(d);
                            realm2.copyToRealmOrUpdate(i);
                            Recent r = realm2.createObject(Recent.class, d.toString());
                            r.setTimestamp(d);
                            realm2.copyToRealmOrUpdate(r);
                            //@todo probably can change this w algo

                            Common c = realm2.createObject(Common.class, d.toString());
                            c.setTimestamp(d);
                            realm2.copyToRealmOrUpdate(c);
                             */
                            //is data stale?
                            //for 'friends' list, since it is small, fixed size list and data could
                            //change, just clobber it

                            if (u.friendsList.size() > 0) {
                                u.friendsList = null;
                            }
                            for (Photo p : photos.getPhotos().getPhotoList()) {
                                u.friendsList.add(p);
                            }
                            if (u.tagsList.size() < tags.size()) {
                                for (Tag t : tags) {
                                    if (!u.tagsList.contains(t)) {
                                        t.setAuthorname(Util.getCurrentUser());
                                        u.tagsList.add(t);
                                    }
                                }
                            }
                            //f.user.username.content =
                            u.name = Util.getCurrentUser();
                            u.timestamp = Calendar.getInstance().getTime();
                            realm2.copyToRealmOrUpdate(u);  //deep copy
                            realm2.commitTransaction();
                            Log.d("DEBUG", "end get userinfo: " + u);
                        } finally {
                            if (realm2 != null) {
                                realm2.close();
                            }
                        }


                    }

                });


    }

}
