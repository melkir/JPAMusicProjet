package com.dao;

import com.bean.Music;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by melkir on 20/03/15.
 */
public class MusicRepositoryImpl implements MusicRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Music update(Integer id, Music m) {
        Music music = em.find(Music.class, id);
        music.getArtist().setName(m.getArtist().getName());
        music.getAlbum().setTitle(m.getAlbum().getTitle());
        music.setTitle(m.getTitle());
        em.persist(music);
        return music;
    }

}
