package com.controller;

import com.bean.Music;
import com.dao.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by melkir on 13/03/15.
 */

@RestController
@RequestMapping(value = "/api/music")
public class RestAPIController {

    @Autowired
    MusicRepository service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Music> list() {
        return service.findAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Music> find(@PathVariable String name) {
        return service.findByTitleOrAlbumOrArtist(name);
    }

}
