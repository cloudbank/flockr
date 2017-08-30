package com.anubis.flickr.service;

/**
 * Created by sabine on 10/6/16.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.anubis.flickr.sync.SyncAdapter;


public class SyncService extends Service {
    private static SyncAdapter sSyncAdapter = null;
    private static final Object sSyncAdapterLock = new Object();

    @Override
    public void onCreate() {

        Log.d("FlickrSyncService", "onCreate - FlickrSyncService");
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        return sSyncAdapter.getSyncAdapterBinder();
    }
}