package com.dao;

import com.bean.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melkir on 30/03/15.
 */
public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    List<Artist> findAll();
}
