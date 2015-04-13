package com.controller;

import com.bean.Artist;
import com.dao.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String show(Model model) {
        final List<Artist> list = artistRepository.findAll();
        model.addAttribute("listArtists", list);
        return "tabartist";
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable int id, Model model) {
        Artist artist = artistRepository.findOne(id);
        model.addAttribute("artist", artist);
        return "artist";
    }

}
