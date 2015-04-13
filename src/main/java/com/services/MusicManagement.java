package com.services;

import com.bean.Music;

/**
 * Created by melkir on 01/04/15.
 */
public interface MusicManagement {

    Music save(Music music);

    Music update(Integer id, Music music);

    long countMusics();

    long countAlbums();

    long countArtists();

    void loadMusicFromJson();

}
