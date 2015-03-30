package com.bean;

import javax.persistence.*;

/**
 * Created by melkir on 30/03/15.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private Artist artist;

    public Product() {
    }

    public Product(String title, String artist) {
        this.title = title;
        this.artist = new Artist(artist);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtist() {
        return artist.getName();
    }

    public void setArtist(String artist) {
        this.artist.setName(artist);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
