package com.anubis.flickr.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;

import com.anubis.flickr.FlickrClientApp;
import com.anubis.flickr.R;
import com.anubis.flickr.activity.LoginActivity;
import com.anubis.oauthkit.OAuthBaseClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sabine on 9/21/16.
 */

public class Util {

    public static String getProperty(String key, Context context) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("config.properties");
        properties.load(inputStream);
        return properties.getProperty(key);

    }

    public static boolean isInit() {
        return !(getUserPrefs().contains(FlickrClientApp.getAppContext().getResources().getString(R.string.current_user)));
    }

    public static boolean isNewUser(String username) {
        return !getUserPrefs().getString(FlickrClientApp.getAppContext().getResources().getString(R.string.current_user), "").equals(username);
    }

    public static String getCurrentUser() {
        SharedPreferences prefs = Util.getUserPrefs();
        return prefs.getString(FlickrClientApp.getAppContext().getResources().getString(R.string.current_user), "");
    }

    public static String getUserId() {
        SharedPreferences prefs = Util.getUserPrefs();
        return prefs.getString(FlickrClientApp.getAppContext().getResources().getString(R.string.user_id), "");
    }

    public static SharedPreferences getUserPrefs() {
        return FlickrClientApp.getAppContext().getSharedPreferences(FlickrClientApp.getAppContext().getResources().getString(R.string.Phlix_User_Prefs), 0);
    }


    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }



    public static void signOut(Context ctx) {

        OAuthBaseClient.getInstance(ctx, null).clearTokens();
        Intent bye = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(bye);
    }
}
