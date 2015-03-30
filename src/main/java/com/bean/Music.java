package com.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by melkir on 15/03/15.
 */
@Entity
public class Music extends Product {
    @OneToOne
    private Album album;

    public Music() {
    }

    public Music(String title, Artist artist, Album album) {
        super(title, artist);
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
