package com.anubis.phlix.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.anubis.phlix.models.Recent;
import com.anubis.phlix.models.Tag;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.hkm.soltag.TagContainerLayout;
import co.hkm.soltag.TagView;
import io.realm.Realm;
import io.realm.RealmChangeListener;


public class TagsFragment extends FlickrBaseFragment {

    List mTags;
    TagContainerLayout mTagsView;
    private List<Photo> mPhotos;

    AdView mPublisherAdView;
    ProgressDialog ringProgressDialog;
    TagsAdapter tAdapter;
    RecyclerView rvPhotos;

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

        Log.d("TABS", "tags activcreated");
        changeListener = new RealmChangeListener<Recent>()

        {
            @Override
            public void onChange(Recent r) {
                updateDisplay(r);
            }
        };


        tagsRealm = Realm.getDefaultInstance();
        ringProgressDialog.setTitle("Please wait");
        ringProgressDialog.setMessage("Retrieving tags/recent photos");
        ringProgressDialog.setCancelable(true);
        ringProgressDialog.show();
        Date maxDate = tagsRealm.where(Recent.class).maximumDate(getString(R.string.timestamp));
        Recent recent = tagsRealm.where(Recent.class).equalTo(getString(R.string.timestamp), maxDate).findFirst();
        if (null == recent) {
            r = Realm.getDefaultInstance();
            RealmChangeListener realmListener = new RealmChangeListener<Realm>() {
                @Override
                public void onChange(Realm r) {
                    updateDisplay();
                }
            };
            r.addChangeListener(realmListener);

        } else {
            Log.d("TAGS PRESENT", "list: " + recent);
            updateDisplay(recent);
            recent.addChangeListener(changeListener);
            if (null != r) {
                r.removeAllChangeListeners();
                r.close();
            }
        }
        ringProgressDialog.dismiss();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotos = new ArrayList<Photo>();
        ringProgressDialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
        tAdapter = new TagsAdapter(getActivity(), mPhotos, false);

        mTags = new ArrayList<Tag>();
        Log.d("TABS", "tags oncreate");
        setRetainInstance(true);
    }


    void customLoadMoreDataFromApi(int page) {
    }


    private void updateDisplay() {
        Date maxDate = tagsRealm.where(Recent.class).maximumDate(getString(R.string.timestamp));
        Recent r = tagsRealm.where(Recent.class).equalTo(getString(R.string.timestamp), maxDate).findFirst();
        displayHotTags(r.getHotTagList());
        mPhotos.clear();
        mPhotos.addAll(r.getRecentPhotos());
        tAdapter.notifyDataSetChanged();
    }


    private void updateDisplay(Recent r) {
        displayHotTags(r.getHotTagList());
        mPhotos.clear();
        mPhotos.addAll(r.getRecentPhotos());
        tAdapter.notifyDataSetChanged();


    }


    public void displayHotTags(List<Tag> tags) {
        //tags.stream().map(it -> it.getContent()).collect(Collectors.toCollection())
        //when android catches up to 1.8
        mTagsView.removeAllTags();
        for (Tag t : tags) {
            mTagsView.addTag(t.getContent());
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
        tAdapter.setOnItemClickListener(new TagsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),
                        ImageDisplayActivity.class);
                Photo photo = mPhotos.get(position);
                intent.putExtra(RESULT, photo.getId());
                startActivity(intent);
            }
        });

        mPublisherAdView = (AdView) view.findViewById(R.id.publisherAdView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("9D3A392231B42400A9CCA1CBED2D006F")  // My Galaxy Nexus test phone
                .build();
        mPublisherAdView.loadAd(adRequest);
        setHasOptionsMenu(true);
        return view;
    }


}
