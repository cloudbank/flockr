package com.anubis.phlix.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.anubis.phlix.models.Tag;
import com.anubis.phlix.models.UserModel;
import com.anubis.phlix.util.Util;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import co.hkm.soltag.TagContainerLayout;
import io.realm.Realm;
import io.realm.RealmChangeListener;

public class FriendsFragment extends FlickrBaseFragment {

    private String mUserId;

    private List<Photo> mPhotos, cPhotos;

    AdView mPublisherAdView;
    ProgressDialog ringProgressDialog;
    FriendsAdapter fAdapter;
    RecyclerView rvPhotos;
    List<Tag> mTags;

    TagContainerLayout mTagView;
    Realm userRealm, r;
    public UserModel mUser;
    RealmChangeListener changeListener;
    RadioGroup rg;
    RadioButton rb1, rb5;
    View view;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        changeListener = new RealmChangeListener<UserModel>()

        {
            @Override
            public void onChange(UserModel u) {
                // This is called anytime the Realm database changes on any thread.
                // Please note, change listeners only work on Looper threads.
                // For non-looper threads, you manually have to use Realm.waitForChange() instead.

                //have to redraw the view
                //view.invalidate();

                updateDisplay(u);
                if (rb1.isChecked()) {
                    //some code

                    makeSingle(cPhotos);
                    fAdapter.notifyDataSetChanged();
                }
            }
        };


        userRealm = Realm.getDefaultInstance();
        ringProgressDialog.setTitle("Please wait");
        ringProgressDialog.setMessage("Retrieving friend photos");
        ringProgressDialog.setCancelable(true);
        ringProgressDialog.show();
        mUserId = Util.getUserId();
        Log.d("USER ID", "id: " + mUserId);


        mUser = userRealm.where(UserModel.class).equalTo("userId", mUserId).findFirst();
        r = Realm.getDefaultInstance();
        if (null == mUser) {
            RealmChangeListener realmListener = new RealmChangeListener<Realm>() {
                @Override
                public void onChange(Realm r) {
                    updateDisplay();
                    if (rb1.isChecked()) {
                        makeSingle(cPhotos);
                        fAdapter.notifyDataSetChanged();
                    }
                }
            };
            r.addChangeListener(realmListener);

        } else {
            Log.d("USER PRESENT", "user: " + mUser);
            mUser.addChangeListener(changeListener);
            updateDisplay(mUser);
            if (null != r) {
                r.removeAllChangeListeners();
                r.close();
            }


        }
        ringProgressDialog.dismiss();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    makeSingle(cPhotos);
                    fAdapter.notifyDataSetChanged();

                } else if (checkedId == R.id.radio5) {
                    updateDisplay();
                }

            }
        });

    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotos = new ArrayList<Photo>();
        cPhotos = new ArrayList<Photo>();
        mTags = new ArrayList<Tag>();
        fAdapter = new FriendsAdapter(getActivity(), mPhotos, false);
        ringProgressDialog = new ProgressDialog(getActivity(), R.style.CustomProgessBarStyle);

        setRetainInstance(true);
    }


    void customLoadMoreDataFromApi(int page) {
    }


    private void updateDisplay(UserModel u) {
        mPhotos.clear();
        cPhotos.clear();
        displayTags(u.getTagsList());
        cPhotos.addAll(u.getFriendsList());
        mPhotos.addAll(u.getFriendsList());
        fAdapter.notifyDataSetChanged();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(Util.getCurrentUser());
    }

    private void updateDisplay() {
        cPhotos.clear();
        mPhotos.clear();
        UserModel u = userRealm.where(UserModel.class).equalTo("userId", Util.getUserId()).findFirst();
        displayTags(u.getTagsList());
        cPhotos.addAll(u.getFriendsList());
        mPhotos.addAll(u.getFriendsList());
        fAdapter.notifyDataSetChanged();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(Util.getCurrentUser());
    }


    public void displayTags(List<Tag> tags) {
        //tags.stream().map(it -> it.getContent()).collect(Collectors.toCollection())
        //when android catches up to 1.8
        mTagView.removeAllTags();
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
        if (null != this.ringProgressDialog) {
            this.ringProgressDialog = null;
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
        fAdapter.setOnItemClickListener(new FriendsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),
                        ImageDisplayActivity.class);
                Photo photo = mPhotos.get(position);
                intent.putExtra(RESULT, photo.getId());
                startActivity(intent);
            }
        });
        mTagView = (TagContainerLayout) view.findViewById(R.id.my_tag_group);
        rg = (RadioGroup) view.findViewById(R.id.radioGroup1);
        rb1 = (RadioButton) view.findViewById(R.id.radio1);
        rb5 = (RadioButton) view.findViewById(R.id.radio5);

        mPublisherAdView = (AdView) view.findViewById(R.id.publisherAdView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("9D3A392231B42400A9CCA1CBED2D006F")  // My Galaxy Nexus test phone
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


}
