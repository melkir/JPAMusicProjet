package com.controller;

import com.bean.Artist;
import com.dao.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by melkir on 02/04/15.
 */
@Controller
@RequestMapping(value = "/artist")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String show() {
        final List<Artist> list = artistRepository.findAll();
        return list.toString();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable int id) {
        Artist artist = artistRepository.findOne(id);
        return artist.toString();
    }

}
