package com.anubis.oauthkit;

/**
 * Created by sabine on 9/18/16.
 */


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

import java.util.HashMap;

public class OAuthBaseClient {
    protected Context context;
    protected OAuthSignPostOKHttpClient client;
    protected SharedPreferences prefs;
    protected SharedPreferences.Editor editor;
    protected OAuthBaseClient.OAuthAccessHandler accessHandler;
    protected int requestIntentFlags = -1;
    protected static HashMap<Class<? extends OAuthBaseClient>, OAuthBaseClient> instances = new HashMap();
    private static OAuthBaseClient instance;
    private static final String baseUrl = BuildConfig.baseUrl;
    private static String callbackUrl = BuildConfig.callbackUrl;

    public SharedPreferences getPrefs() {
        return this.prefs;
    }


    public OAuthBaseClient.OAuthAccessHandler getAccessHandler() {
        return this.accessHandler;
    }

    public static OAuthBaseClient getInstance(Context context, OAuthBaseClient.OAuthAccessHandler handler) {
        if (instance == null) {
            instance = new OAuthBaseClient(context, handler);
        }
        return instance;
    }

    //@singleton
    private OAuthBaseClient(Context context, OAuthBaseClient.OAuthAccessHandler handler) {
        this.context = context;
        this.accessHandler = handler;
        this.context = context;
        this.prefs = this.context.getSharedPreferences("OAuthKit_Prefs", 0);
        this.editor = this.prefs.edit();
        this.client = new OAuthSignPostOKHttpClient(new OAuthSignPostOKHttpClient.OAuthTokenHandler() {

            //@todo implement interface and ovverride here
            public void onReceivedRequestToken(OkHttpOAuthConsumer consumer, String authorizeUrl) {
                if (consumer != null)

                {
                    OAuthBaseClient.this.editor.putString("request_token", consumer.getToken());
                    OAuthBaseClient.this.editor.putString("request_token_secret", consumer.getTokenSecret());
                    OAuthBaseClient.this.editor.commit();
                }
                //open the authorize intent view with intent data set in filter
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(authorizeUrl + consumer.getToken() + "&perms=delete"));
                if (OAuthBaseClient.this.requestIntentFlags != -1) {
                    intent.setFlags(OAuthBaseClient.this.requestIntentFlags);

                }
                //singleTask
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                OAuthBaseClient.this.context.startActivity(intent);
            }

            public void onReceivedAccessToken(OkHttpOAuthConsumer consumer) {

                OAuthBaseClient.this.editor.putString("oauth_token", consumer.getToken());
                OAuthBaseClient.this.editor.putString("oauth_token_secret", consumer.getTokenSecret());
                OAuthBaseClient.this.editor.remove("request_token");

                OAuthBaseClient.this.editor.commit();


                OAuthBaseClient.this.accessHandler.onLoginSuccess(consumer, baseUrl);
            }

            public void onFailure(Exception e) {
                OAuthBaseClient.this.accessHandler.onLoginFailure(e);
            }
        }, this.prefs);


    }

    public void connect() {
        this.client.fetchRequestToken();
    }

    public void authorize(Uri uri) {
        if (this.checkAccessToken() == null && uri != null) {
            String uriServiceCallback = uri.getScheme() + "://" + uri.getHost();
            if (uriServiceCallback.equals(this.callbackUrl)) {
                this.client.fetchAccessToken(uri);
            }
        } else if (this.checkAccessToken() != null) {
            OAuthBaseClient.this.accessHandler.onLoginSuccess(this.getClient().getConsumer(), this.baseUrl);
        }

    }


    public Token checkAccessToken() {
        return this.prefs.contains("oauth_token") && this.prefs.contains("oauth_token_secret") ? new Token(this.prefs.getString("oauth_token", ""), this.prefs.getString("oauth_token_secret", "")) : null;
    }

    protected OAuthSignPostOKHttpClient getClient() {
        return this.client;
    }


    public void clearTokens() {
        this.editor.remove("oauth_token");
        this.editor.remove("oauth_token_secret");
        this.editor.remove("request_token");
        this.editor.remove("request_token_secret");

        this.editor.commit();
    }


    public interface OAuthAccessHandler {
        void onLoginSuccess(OkHttpOAuthConsumer consumer, String baseUrl);

        void onLoginFailure(Exception var1);
    }


}

