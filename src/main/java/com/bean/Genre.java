package com.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melkir on 30/03/15.
 */
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private List<Artist> artists = new ArrayList<Artist>();

    public Genre() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

}
