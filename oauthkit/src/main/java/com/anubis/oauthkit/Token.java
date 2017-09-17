package com.anubis.oauthkit;

/**
 * Created by sabine on //.
 */

public class Token {
  private String token;
  private String secret;

  public Token(String token, String secret) {
    this.token = token;
    this.secret = secret;
  }

  public String getToken() {
    return token;
  }

  public String getSecret() {
    return secret;
  }
}
        