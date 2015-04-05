package com.services;

import com.bean.Music;

import java.io.IOException;

/**
 * Created by melkir on 01/04/15.
 */
public interface MusicManagement {

    Music save(Music music);

    Music update(Integer id, Music music);

    void loadMusicFromJson();

}
