package com.controller;

import com.bean.Album;
import com.dao.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by melkir on 01/04/15.
 */
@RestController
@RequestMapping(value = "/api/album")
public class RestAPIAlbumController {

    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Album> list() {
        return albumRepository.findAll();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Album findById(@PathVariable int id) {
        return albumRepository.findOne(id);
    }

}
