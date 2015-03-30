package com.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by melkir on 30/03/15.
 */
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String biography;
    @OneToMany(mappedBy = "artist")
    private List<Product> products = new ArrayList<>();
    @ManyToMany(mappedBy = "artists")
    private List<Genre> genres = new ArrayList<>();

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

}
