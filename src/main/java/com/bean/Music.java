package com.bean;

import javax.persistence.Entity;

/**
 * Created by melkir on 15/03/15.
 */
@Entity
public class Music extends Product {
    private String album;

    public Music() {
    }

    public Music(String title, String artist, String album) {
        super(title, artist);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
