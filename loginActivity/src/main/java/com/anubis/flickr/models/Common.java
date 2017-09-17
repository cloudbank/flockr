package com.anubis.flickr.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sabine on 10/6/16.
 */
public class Common extends RealmObject implements RealmModel {
  @PrimaryKey
  public String id;
  public Date timestamp;

  public RealmList<Photo> getCommonPhotos() {
    return commonPhotos;
  }

  public void setCommonPhotos(RealmList<Photo> commonPhotos) {
    this.commonPhotos = commonPhotos;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public RealmList<Photo> commonPhotos;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
