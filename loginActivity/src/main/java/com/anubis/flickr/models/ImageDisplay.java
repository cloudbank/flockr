package com.anubis.flickr.models;

/**
 * Created by sabine on 9/29/16.
 */

public class ImageDisplay {
    private PhotoInfo photo;
    private Comments comments;

    public ImageDisplay(PhotoInfo p, Comments c) {
        this.photo = p;

        this.comments = c;
    }

    public PhotoInfo getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoInfo photo) {
        this.photo = photo;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }





}
