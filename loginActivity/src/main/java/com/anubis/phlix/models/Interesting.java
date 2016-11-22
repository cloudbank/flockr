package com.anubis.phlix.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sabine on 10/6/16.
 */

public class Interesting extends RealmObject implements RealmModel {

    @PrimaryKey
    public String id;

    public Date timestamp;

    public RealmList<Photo> getInterestingPhotos() {
        return interestingPhotos;
    }

    public void setInterestingPhotos(RealmList<Photo> interestingPhotos) {
        this.interestingPhotos = interestingPhotos;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RealmList<Photo> interestingPhotos;
}
