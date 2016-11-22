package com.anubis.phlix.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anubis.phlix.FlickrClientApp;
import com.anubis.phlix.R;
import com.anubis.phlix.activity.ImageDisplayActivity;
import com.anubis.phlix.adapter.InterestingAdapter;
import com.anubis.phlix.listener.EndlessRecyclerViewScrollListener;
import com.anubis.phlix.models.Interesting;
import com.anubis.phlix.models.Photo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class InterestingFragment extends FlickrBaseFragment {
    InterestingAdapter rAdapter;
    RecyclerView rvPhotos;
    ProgressDialog ringProgressDialog;
    List<Photo> mInteresting = new ArrayList<Photo>();
    RealmChangeListener changeListener;
    Realm interestingRealm, r;


    @Override
    public void onResume() {
        super.onResume();
        Log.d("TABS", "interesting onresume");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TABS", "interesting activcreated");
        changeListener = new RealmChangeListener<Interesting>() {
            @Override
            public void onChange(Interesting i) {
                updateDisplay(i);
            }
        };


        interestingRealm = Realm.getDefaultInstance();
        ringProgressDialog.setTitle("Please wait");
        ringProgressDialog.setMessage("Retrieving friend photos");
        ringProgressDialog.setCancelable(true);
        ringProgressDialog.show();
        Date maxDate = interestingRealm.where(Interesting.class).maximumDate("timestamp");
        Interesting interesting = interestingRealm.where(Interesting.class).equalTo("timestamp", maxDate).findFirst();
        if (null == interesting) {
            r = Realm.getDefaultInstance();
            RealmChangeListener realmListener = new RealmChangeListener<Realm>() {
                @Override
                public void onChange(Realm r) {
                    updateDisplay();
                }
            };
            r.addChangeListener(realmListener);

        } else {
            Log.d("INTERESTING PRESENT", "list: " + interesting);
            updateDisplay(interesting);
            interesting.addChangeListener(changeListener);
            if (null != r) {
                r.removeAllChangeListeners();
                r.close();
            }
        }
        ringProgressDialog.dismiss();

    }


    private void updateDisplay() {
        Date maxDate = interestingRealm.where(Interesting.class).maximumDate("timestamp");
        Interesting i = interestingRealm.where(Interesting.class).equalTo("timestamp", maxDate).findFirst();
        mInteresting.clear();
        mInteresting.addAll(i.getInterestingPhotos());
        rAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d("TABS", "interesting oncreate");
        ringProgressDialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
        rAdapter = new InterestingAdapter(FlickrClientApp.getAppContext(), mInteresting, true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interesting, container,
                false);
        rvPhotos = (RecyclerView) view.findViewById(R.id.rvPhotos);
        rvPhotos.setAdapter(rAdapter);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvPhotos.setLayoutManager(gridLayoutManager);
        rvPhotos.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });
        rAdapter.setOnItemClickListener(new InterestingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),
                        ImageDisplayActivity.class);
                Photo photo = mInteresting.get(position);

                intent.putExtra(RESULT, photo.getId());
                startActivity(intent);
            }
        });
        setHasOptionsMenu(true);
        return view;
    }


    void customLoadMoreDataFromApi(int page) {
    }


    public void updateDisplay(Interesting i) {
        mInteresting.clear();
        mInteresting.addAll(i.getInterestingPhotos());
        rAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != r && !r.isClosed()) {
            r.close();
        }
        if (null != ringProgressDialog) {
            ringProgressDialog = null;
        }
        if (null != interestingRealm && !interestingRealm.isClosed()) {
            interestingRealm.close();
        }
    }

}



