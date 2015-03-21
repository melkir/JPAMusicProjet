package com.dao;

import com.bean.Music;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by melkir on 13/03/15.
 */
public interface MusicRepository extends CrudRepository<Music, Integer>, MusicRepositoryCustom {

    List<Music> findByTitle(String title);

    List<Music> findByAlbum(String album);

    List<Music> findByArtist(String artist);

    List<Music> findAll();

    @Query("SELECT m FROM Music m WHERE m.title LIKE CONCAT('%',:name,'%') OR m.album LIKE CONCAT('%',:name,'%') OR m.artist LIKE CONCAT('%',:name,'%')")
    List<Music> findByTitleOrAlbumOrArtist(@Param("name") String name);

}
