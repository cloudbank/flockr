package com.anubis.phlix.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sabine on 10/6/16.
 */

public class Recent extends RealmObject implements RealmModel {

    @PrimaryKey
    public String id;

    public Date timestamp;

    public RealmList<Photo> getRecentPhotos() {
        return recentPhotos;
    }

    public void setRecentPhotos(RealmList<Photo> recentPhotos) {
        this.recentPhotos = recentPhotos;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RealmList<Photo> recentPhotos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<Tag> getHotTagList() {
        return hotTagList;
    }

    public void setHotTagList(RealmList<Tag> hotTagList) {
        this.hotTagList = hotTagList;
    }

    public RealmList<Tag> hotTagList;
}
