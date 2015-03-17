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
    public List<Music> findMusic(@PathVariable String name) {
        return service.findByTitleOrAlbumOrArtist(name);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Music findById(@PathVariable int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/album/{name}", method = RequestMethod.GET)
    public List<Music> findByAlbum(@PathVariable String name) {
        return service.findByAlbum(name);
    }

    @RequestMapping(value = "/artist/{name}", method = RequestMethod.GET)
    public List<Music> findByArtist(@PathVariable String name) {
        return service.findByArtist(name);
    }

    @RequestMapping(value = "/title/{name}", method = RequestMethod.GET)
    public List<Music> findByTitle(@PathVariable String name) {
        return service.findByTitle(name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
            return String.format("Music [%s] successfully deleted", id); // uses the delete() method inherited from CrudRepository
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Music [%s]", e.getMessage());
        }
    }

}
