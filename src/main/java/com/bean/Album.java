package com.bean;

import javax.persistence.Entity;

/**
 * Created by melkir on 30/03/15.
 */
@Entity
public class Album extends Product {
    int tracks;

    public Album() {
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

}
