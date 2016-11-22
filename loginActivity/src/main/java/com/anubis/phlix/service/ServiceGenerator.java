package com.anubis.phlix.service;


import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.schedulers.Schedulers;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


/**
 * Created by sabine on 9/18/16.
 */

public class ServiceGenerator {


    public static <T> T createRetrofitRxService(OkHttpOAuthConsumer consumer, final Class<T> clazz, String baseUrl, Converter.Factory converter) {

        //configured with a consumer that has access token set
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();


        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(rxAdapter)
                .build();

        T service = retrofit.create(clazz);
             return service;
    }




}



