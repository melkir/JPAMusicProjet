package com.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melkir on 30/03/15.
 */
@Entity
public class Album extends Product {
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Music> musics = new ArrayList<Music>();

    public Album() {
        this.setType("Album");
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public void addMusic(Music music) {
        musics.add(music);
    }

    public void removeMusic(Music music) {
        musics.remove(music);
    }

    @Override
    public String toString() {
        String res;
        res = "Album{"
                + "id=" + this.getId() + ";"
                + "title=" + this.getTitle() + ";"
                + "artist=" + this.getArtist().getName() + ";"
                + "musics=";
        for (Music music : musics) res += music.toString() + ",";
        res += "}\n";
        return res;
    }
}
