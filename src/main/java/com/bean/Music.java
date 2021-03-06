package com.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by melkir on 15/03/15.
 */
@Entity
public class Music extends Product {
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "title")
    private Album album;

    public Music() {
        this.setType("Musique");
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

    @Override
    public String toString() {
        String res;
        res = "Music{id=" + this.getId() + ";"
                + "title=" + this.getTitle() + ";"
                + "artist=" + this.getArtist().getName() + ";"
                + "album=" + this.getAlbum().getTitle() + "}\n";
        return res;
    }
}
