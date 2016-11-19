package com.anubis.flickr;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;

import com.anubis.flickr.service.FlickrService;
import com.anubis.flickr.service.ServiceGenerator;
import com.facebook.stetho.Stetho;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

public class FlickrClientApp extends Application {


    private static FlickrService jacksonService;
    private static FlickrService defaultService;
    // The authority for the sync adapter's content provider
    // An account type, in the form of a domain name
    // The account name
    // Instance fields
    Account mAccount;


    private static Context context;


    public static Context getAppContext() {
        return FlickrClientApp.context;
    }

    //prevent leaking activity context http://bit.ly/6LRzfx


    public static FlickrService getJacksonService() {
        return (FlickrService) jacksonService;
    }

    public static FlickrService getDefaultService() {
        return (FlickrService) defaultService;
    }


    public static void setJacksonService(OkHttpOAuthConsumer consumer, String baseUrl) {
        jacksonService = ServiceGenerator.createRetrofitRxService(consumer, FlickrService.class, baseUrl, JacksonConverterFactory.create());
    }

    public static void setDefaultService(OkHttpOAuthConsumer consumer, String baseUrl) {
        defaultService = ServiceGenerator.createRetrofitRxService(consumer, FlickrService.class, baseUrl, SimpleXmlConverterFactory.create());
    }


    @Override
    public void onCreate() {
        super.onCreate();


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);


        FlickrClientApp.context = getApplicationContext();
        Stetho.initializeWithDefaults(this);


        //TypefaceUtil.setDefaultFont(this, "SERIF", "fonts/Exo-Medium.otf");
        Picasso.Builder builder = new Picasso.Builder(this);
        //wharton lib requires picasso 2.5.2 right now
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(false);
        Picasso.setSingletonInstance(built);


    }


}
