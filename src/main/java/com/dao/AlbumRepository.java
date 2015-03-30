package com.dao;

import com.bean.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melkir on 30/03/15.
 */
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    Album findByTitle(String title);

    List<Album> findAll();

}
