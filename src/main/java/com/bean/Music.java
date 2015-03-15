package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by melkir on 13/03/15.
 */

@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title, album, artist;

    public Music() {
    }

    public Music(String title, String album, String artist) {
        this.title = title;
        this.album = album;
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }

}
