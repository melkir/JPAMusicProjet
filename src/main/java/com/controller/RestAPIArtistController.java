package com.controller;

import com.bean.Artist;
import com.dao.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melkir on 01/04/15.
 */
@RestController
@RequestMapping(value = "/api/artist")
public class RestAPIArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Artist> list() {
        return artistRepository.findAll();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Artist findById(@PathVariable int id) {
        return artistRepository.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createArtist(@RequestBody Artist artist) {
        try {
            artistRepository.save(artist);
            return String.format("Artist [%s] successfully created", artist.toString());
        } catch (Exception e) {
            return String.format("A problem occurred when creating Artist [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editArtist(@PathVariable("id") Integer id, @RequestBody Artist a) {
        try {
            Artist artist = artistRepository.findOne(id);
            artist.setName(a.getName());
            artist.setBiography(a.getBiography());
            artistRepository.save(artist);
            return String.format("Artist [%s] successfully updated", id);
        } catch (Exception e) {
            return String.format("A problem occurred when updating Artist [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") Integer id) {
        try {
            artistRepository.delete(id);
            return String.format("Artist [%s] successfully deleted", id); // uses the delete() method inherited from CrudRepository
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Artist [%s]", e.getMessage());
        }
    }

}
