package com.controller;

import com.bean.Artist;
import com.dao.ArtistRepository;
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
@RequestMapping(value = "/api/artist")
public class RestAPIArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Artist> list() {
        return artistRepository.findAll();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Artist findById(@PathVariable int id) {
        return artistRepository.findOne(id);
    }

}
