package com.anubis.flickr;

import android.support.multidex.MultiDexApplication;

import com.anubis.flickr.service.FlickrService;
import com.anubis.flickr.service.ServiceGenerator;
import com.anubis.flickr.util.Util;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.tensorflow.tensorlib.TensorLib;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

import static com.anubis.flickr.service.ServiceGenerator.createRetrofitRxService;
import static com.anubis.oauthkit.BuildConfig.baseUrl;

public class FlickrClientApp extends MultiDexApplication {

    private static FlickrClientApp instance;


    public static FlickrClientApp getAppContext() {
        return instance;
    }

    private static com.anubis.flickr.service.FlickrService jacksonService;
    private static com.anubis.flickr.service.FlickrService defaultService;

    //prevent leaking activity context http://bit.ly/6LRzfx


    //dealing with Application being reclaimed and static vars reset
    //
    public static FlickrService getJacksonService() {
        return ((jacksonService == null) ? createJacksonService(getConsumer()) : jacksonService);
    }

    public static FlickrService getDefaultService() {

        return ((defaultService == null) ? createDefaultService(getConsumer()) : defaultService);
    }

    private static OkHttpOAuthConsumer getConsumer() {
        Gson gson = new Gson();
        String json = Util.getUserPrefs().getString(FlickrClientApp.getAppContext().getString(R.string.Consumer), "");
        return gson.fromJson(json, OkHttpOAuthConsumer.class);
    }


    //@todo change docs for oauthkit with release
    public static FlickrService createJacksonService(OkHttpOAuthConsumer consumer) {
        jacksonService = ServiceGenerator.createRetrofitRxService(consumer, com.anubis.flickr.service.FlickrService.class, baseUrl, JacksonConverterFactory.create());
        return jacksonService;
    }

    public static FlickrService createDefaultService(OkHttpOAuthConsumer consumer) {
        defaultService = createRetrofitRxService(consumer, com.anubis.flickr.service.FlickrService.class, baseUrl, SimpleXmlConverterFactory.create());
        return defaultService;
    }





    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        TensorLib.init(this);
        Stetho.initializeWithDefaults(this);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Picasso.Builder builder = new Picasso.Builder(this);
        //wharton lib requires picasso 2.5.2 right now
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        //built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(false);
        Picasso.setSingletonInstance(built);


    }


}
