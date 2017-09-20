package com.anubis.flickr.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sabine on 10/6/16.
 */
public class Paintings extends RealmObject implements RealmModel {
  @PrimaryKey
  public String id;
  public Date timestamp;

  public RealmList<Photo> getPaintingPhotos() {
    return paintingPhotos;
  }

  public void setPaintingPhotos(RealmList<Photo> paintingPhotos) {
    this.paintingPhotos = paintingPhotos;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public RealmList<Photo> paintingPhotos;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
