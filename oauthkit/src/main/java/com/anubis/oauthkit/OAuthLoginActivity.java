package com.anubis.oauthkit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by sabine on 9/20/16.
 */


public abstract class OAuthLoginActivity extends FragmentActivity
    implements OAuthBaseClient.OAuthAccessHandler {

  private OAuthBaseClient client;

  @Override
  protected void onDestroy() {
    super.onDestroy();
    //prevent leaking activity
    if (null != this.client.getAccessHandler()) {
      OAuthBaseClient.OAuthAccessHandler handler = this.client.getAccessHandler();
      handler = null;
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.client = OAuthBaseClient.getInstance(this.getApplicationContext(), this);
  }


  // Use this to properly assign the new intent with callback code
  // for activities with a "singleTask" launch mode
  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    setIntent(intent);
  }

  // Extract the uri data and call authorize to retrieve access token
  // This is why after the browser redirects to the app, authentication is completed
  @SuppressWarnings("unchecked")
  @Override
  protected void onResume() {
    super.onResume();


    //@todo only need to check this when authing; remove request token when receive acccess token
    if (this.client.getPrefs().contains("request_token")) {
      Uri uri = getIntent().getData();
      try {
        client.authorize(uri); // fetch access token (if needed)
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


}

