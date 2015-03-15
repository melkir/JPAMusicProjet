package com.dao;

import com.bean.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by melkir on 13/03/15.
 */
public interface IMusicDAO extends CrudRepository<Music, Long> {

    List<Music> findByTitle(String title);

    List<Music> findByAlbum(String album);

    List<Music> findByArtist(String artist);

    List<Music> findAll();

//    List<Music> findByTitleOrAlbumOrArtist(String name);

}
