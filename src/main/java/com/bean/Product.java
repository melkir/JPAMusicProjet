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
    @OneToOne(cascade = CascadeType.ALL)
    private Artist artist;

    public Product() {
    }

    public Product(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
