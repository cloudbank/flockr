package com.anubis.oauthkit;


import android.content.SharedPreferences;
import android.net.Uri;

import oauth.signpost.http.HttpParameters;

import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthProvider;


/**
 * Created by sabine on 9/18/16
 */

public class OAuthSignPostOKHttpClient {    //


  private OkHttpOAuthConsumer consumer;
  private OkHttpOAuthProvider provider;
  private OAuthSignPostOKHttpClient.OAuthTokenHandler handler;
  private SharedPreferences prefs;

  private static String callbackUrl = BuildConfig.callbackUrl;
  private static final String consumerKey = BuildConfig.consumerKey;
  private static final String consumerSecret = BuildConfig.consumerSecret;
  private static final String requestTokenEndpoint = BuildConfig.requestTokenEndpoint;
  private static final String accessTokenEndpoint = BuildConfig.accessTokenEndpoint;
  private static final String authorizationEndpoint = BuildConfig.authorizationEndpoint;


  public OAuthSignPostOKHttpClient(OAuthSignPostOKHttpClient.OAuthTokenHandler handler, SharedPreferences prefs) {
    this.handler = handler;
    this.prefs = prefs;
    if (callbackUrl == null) {
      callbackUrl = "oob";
    }

    this.consumer = new OkHttpOAuthConsumer(consumerKey, consumerSecret);
    this.provider = new OkHttpOAuthProvider(requestTokenEndpoint, accessTokenEndpoint, authorizationEndpoint);
  }

  public OkHttpOAuthConsumer getConsumer() {
    return this.consumer;
  }


  public OkHttpOAuthProvider getProvider() {
    return this.provider;
  }

  String getCallback() {
    return this.callbackUrl;
  }

  public void fetchRequestToken() {
    new AsyncSimpleTask(new AsyncSimpleTask.AsyncSimpleTaskHandler() {
      Exception e = null;


      public void doInBackground() {
        try {
          provider.retrieveRequestToken(getConsumer(), getCallback(), (new String[]{}));
          // @todo version2

        } catch (Exception var2) {
          this.e = var2;
        }

      }

      public void onPostExecute() {
        if (this.e != null) {
          OAuthSignPostOKHttpClient.this.handler.onFailure(this.e);
        } else {

          OAuthSignPostOKHttpClient.this.handler.onReceivedRequestToken(getConsumer(), getProvider().getAuthorizationWebsiteUrl());
        }

      }
    });
  }


  public void fetchAccessToken(final Uri uri) {
    new AsyncSimpleTask(new AsyncSimpleTask.AsyncSimpleTaskHandler() {
      Exception e = null;

      public void doInBackground() {
        Uri authorizedUri = uri;
        String oauth_verifier = null;
        if (authorizedUri.getQuery().contains("code")) {
          oauth_verifier = authorizedUri.getQueryParameter("code");
        } else if (authorizedUri.getQuery().contains("oauth_verifier")) {
          oauth_verifier = authorizedUri.getQueryParameter("oauth_verifier");
        }

        try {
          if (oauth_verifier == null) {
            throw new Exception("No verifier code was returned with uri \'" + uri + "\' " + "and access token cannot be retrieved");
          }
          provider.retrieveAccessToken(getConsumer(), oauth_verifier);
          setHttpResponseParams(provider.getResponseParameters());


        } catch (Exception var4) {
          this.e = var4;
        }

      }

      public void onPostExecute() {
        if (this.e != null) {
          OAuthSignPostOKHttpClient.this.handler.onFailure(this.e);
        } else {

          OAuthSignPostOKHttpClient.this.handler.onReceivedAccessToken(getConsumer());
        }

      }
    });
  }


  private void setHttpResponseParams(HttpParameters params) {
    SharedPreferences.Editor editor = this.prefs.edit();
    for (String k : params.keySet()) {
      editor.putString(k, params.get(k).first());
    }

    editor.commit();


  }


  public interface OAuthTokenHandler {
    void onReceivedRequestToken(OkHttpOAuthConsumer consumer, String authorizeUrl);

    void onReceivedAccessToken(OkHttpOAuthConsumer consumer);

    void onFailure(Exception var1);
  }
}


