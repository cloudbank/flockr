package com.anubis.flickr.activity;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anubis.flickr.FlickrClientApp;
import com.anubis.flickr.R;
import com.anubis.flickr.fragments.FlickrBaseFragment;
import com.anubis.flickr.fragments.FriendsFragment;
import com.anubis.flickr.fragments.InterestingFragment;
import com.anubis.flickr.fragments.SearchFragment;
import com.anubis.flickr.fragments.TagsFragment;
import com.anubis.flickr.sync.SyncAdapter;
import com.anubis.flickr.util.Util;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Random;

public class PhotosActivity extends AppCompatActivity implements FlickrBaseFragment.OnPhotoPostedListener, ComponentCallbacks2 {

    private MyPagerAdapter adapterViewPager;
    private ViewPager vpPager;

    protected SharedPreferences prefs;
    protected SharedPreferences.Editor editor;
    View rootView;
    public static final String TAG = "PhotosActivity";


    /**
     * @param level the memory-related event that was raised.
     * @todo Release memory when the UI becomes hidden or when system resources become low.
     */
    public void onTrimMemory(int level) {

        // Determine which lifecycle or system event was raised.
        switch (level) {

            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:

                /*
                   Release any UI objects that currently hold memory.

                   The user interface has moved to the background.
                */

                break;

            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:

                /*
                   Release any memory that your app doesn't need to run.

                   The device is running low on memory while the app is running.
                   The event raised indicates the severity of the memory-related event.
                   If the event is TRIM_MEMORY_RUNNING_CRITICAL, then the system will
                   begin killing background processes.
                */

                break;

            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:

                /*
                   Release as much memory as the process can.

                   The app is on the LRU list and the system is running low on memory.
                   The event raised indicates where the app sits within the LRU list.
                   If the event is TRIM_MEMORY_COMPLETE, the process will be one of
                   the first to be terminated.
                */

                break;

            default:
                /*
                  Release any non-critical data structures.

                  The app received an unrecognized memory level value
                  from the system. Treat this as a generic low-memory message.
                */
                break;
        }
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    public void onPhotoPosted() {
        //just add the photo bitmap to realm
        Log.d("POST", "callback");
        vpPager.setCurrentItem(0);
    }

    // Identifier for the permission request
    private static final int GET_ACCOUNTS_PERMISSIONS_REQUEST = 1;

    //@todo refactor
    // Called when the user is performing an action which requires the app to
    //--get accounts which is under contacts; runtime permission only in M
    @TargetApi(Build.VERSION_CODES.M)
    private boolean isAaccountsRuntimePermission() {
        final String perm = Manifest.permission.GET_ACCOUNTS;
        if (ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED) {
            // We have the permission
            return true;
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
            // Need to show permission rationale, display a snackbar and then request
            // the permission again when the snackbar is dismissed to re-request it
            Snackbar.make(rootView, R.string.acct_permission_grant, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", v -> {
                        // Request the permission again.
                        ActivityCompat.requestPermissions(getParent(),
                                new String[]{perm},
                                GET_ACCOUNTS_PERMISSIONS_REQUEST);
                    }).show();
            return false;
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{perm},
                    GET_ACCOUNTS_PERMISSIONS_REQUEST);
            return false;
        }
    }



    // Callback with the request from calling requestPermissions(...)
    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original GET_ACCTS request
        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, permissions[0], Toast.LENGTH_SHORT).show();
            //callback to continue
        } else {
            // showRationale = false if user clicks Never Ask Again, otherwise true
            boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);

            if (showRationale) {
                //did not grant, but did not say never ask
                ActivityCompat.requestPermissions(getParent(),
                        new String[]{permissions[0]},
                        requestCode);

            } else {
                //@todo do we want to continue without?
                Toast.makeText(this, permissions[0] + " permission denied, app must quit!", Toast.LENGTH_SHORT).show();
                Util.signOut(FlickrClientApp.getAppContext());
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        //for snackbar
        rootView = findViewById(android.R.id.content);
        //oauthkit shared prefs
        SharedPreferences authPrefs = getApplicationContext().getSharedPreferences(getString(R.string.OAuthKit_Prefs), 0);


        if (Util.getCurrentUser().length() > 0 && !Util.getCurrentUser().equals(authPrefs.getString(getString(R.string.username), ""))) {
            //@todo stop the sync adapter and restart
            Log.d("SYNC", "changing accounts for sync adapter");
            //find out how to properly stop before restart
            AccountManager am = AccountManager.get(this.getApplicationContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                isAaccountsRuntimePermission();
            }
            Account[] accounts = new Account[]{};
            try {
                accounts = am.getAccounts();
            } catch (SecurityException e) {
                Log.e("SYNC", "account change removal error");
            }
            if (accounts.length > 0) {
                Account accountToRemove = accounts[0];
                am.removeAccount(accountToRemove, null, null);
            }
            ContentResolver.cancelSync(new Account(authPrefs.getString(getString(R.string.username), ""), getApplication().getString(R.string.account_type)), getApplication().getString(R.string.authority));
            // could also cancelSync(null);

        }
        updateUserInfo(authPrefs);


        setContentView(R.layout.activity_photos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            toolbar.setNavigationIcon(R.drawable.rocket_transparent);
        } else {
            getSupportActionBar().setLogo(R.drawable.rocket_transparent);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText(R.string.friends_and_you));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.interesting_today));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tags));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), intializeItems());
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        vpPager.setOffscreenPageLimit(3);
        vpPager.setAdapter(adapterViewPager);
        vpPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //getWindow().setBackgroundDrawable(null);
        tabLayout.addOnTabSelectedListener(onTabSelectedListener(vpPager));
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8660045387738182~7164386158");
        //delaySync(FlickrClientApp.getAppContext());
    }

    long delay;
    long c_delayMax = 600 * 1000;
    static Random r = new Random();

    void delaySync(android.content.Context c) {
        HandlerThread handlerThread = new HandlerThread("delaySync");
        handlerThread.start();
        Handler h = new Handler(handlerThread.getLooper());
        delay = r.nextLong() % c_delayMax;
        if (delay < 0) {
            delay = Math.abs(delay);
        }
        //new Runnable run
        h.postDelayed(() -> {

            Log.d("SYNC", "starting after delay " + delay);
            SyncAdapter.initializeSyncAdapter(c);


        }, delay);

    }


    private void updateUserInfo(SharedPreferences authPrefs) {

        this.prefs = Util.getUserPrefs();
        this.editor = this.prefs.edit();

        editor.putString(getApplicationContext().getString(R.string.current_user), authPrefs.getString(getApplicationContext().getString(R.string.username), ""));
        editor.putString(getApplicationContext().getString(R.string.user_id), authPrefs.getString(getApplicationContext().getString(R.string.user_nsid), ""));

        editor.commit();
        //apply() in a bg thread


    }


    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager pager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }


    public ArrayList<Fragment> intializeItems() {
        ArrayList<Fragment> a = new ArrayList<Fragment>();
        a.add(FriendsFragment.newInstance(0, getResources().getString(R.string.friends_and_you), new FriendsFragment()));
        a.add(InterestingFragment.newInstance(1, getResources().getString(R.string.interesting_today), new InterestingFragment()));
        a.add(SearchFragment.newInstance(2, getResources().getString(R.string.tags), new TagsFragment()));
        return a;
    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }

    //keep small number of pages in memory mostly
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        public static int NUM_ITEMS = 4;
        public FragmentManager mFragmentManager;
        private ArrayList<Fragment> mPagerItems;

        public MyPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> pagerItems) {
            super(fragmentManager);
            this.mFragmentManager = fragmentManager;
            mPagerItems = pagerItems;
        }

        public void setPagerItems(ArrayList<Fragment> pagerItems) {
            if (mPagerItems != null)
                for (int i = 0; i < mPagerItems.size(); i++) {
                    mFragmentManager.beginTransaction().remove(mPagerItems.get(i))
                            .commit();
                }
            mPagerItems = pagerItems;
        }

        // Returns the fragment to display for that page

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mPagerItems.get(0);
                case 1:
                    return mPagerItems.get(1);
                case 2:
                    return mPagerItems.get(2);
                case 3:
                    return mPagerItems.get(3);
                default:
                    return null;
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return getItem(position).getArguments().getString(FlickrClientApp.getAppContext().getString(R.string.title));
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return mPagerItems.size();
        }

        public Fragment getPagerItem(int i) {
            return mPagerItems.get(i);
        }

        public void setPagerItem(FriendsFragment f) {
            mPagerItems.remove(0);
            mPagerItems.add(0, f);

        }

    }

    @Override
    protected void onDestroy() {
        //this.subscription.unsubscribe();
        super.onDestroy();

    }
}
