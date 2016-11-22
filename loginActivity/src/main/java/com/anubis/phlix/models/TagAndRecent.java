package com.anubis.phlix.models;

/**
 * Created by sabine on 10/20/16.
 */

public class TagAndRecent  {



    private Photos recent;
    private Hottags hottags;

    public TagAndRecent(Photos p, Hottags h ) {
        this.recent = p;
        this.hottags = h;

    }

    public Photos getRecent() {
        return recent;
    }

    public void setRecent(Photos recent) {
        this.recent = recent;
    }

    public Hottags getHottags() {
        return hottags;
    }

    public void setHottags(Hottags hottags) {
        this.hottags = hottags;
    }
}
