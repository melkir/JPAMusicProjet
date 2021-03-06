package com.controller;

import com.bean.Music;
import com.dao.MusicRepository;
import com.services.MusicManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melkir on 13/03/15.
 */

@RestController
@RequestMapping(value = "/api/music")
public class RestAPIMusicController {

    @Autowired
    MusicRepository musicRepository;
    @Autowired
    MusicManagement service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Music> list() {
        return musicRepository.findAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Music> findMusic(@PathVariable String name) {
        return musicRepository.findByTitleOrAlbumOrArtist(name);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Music findById(@PathVariable int id) {
        return musicRepository.findOne(id);
    }

    @RequestMapping(value = "/album/{title}", method = RequestMethod.GET)
    public List<Music> findByAlbum(@PathVariable String title) {
        return musicRepository.findByAlbumTitle(title);
    }

    @RequestMapping(value = "/artist/{name}", method = RequestMethod.GET)
    public List<Music> findByArtist(@PathVariable String name) {
        return musicRepository.findByArtistName(name);
    }

    @RequestMapping(value = "/title/{name}", method = RequestMethod.GET)
    public List<Music> findByTitle(@PathVariable String name) {
        return musicRepository.findByTitle(name);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createMusic(@RequestBody Music music) {
        try {
            service.save(music);
            return String.format("Music [%s] successfully created", music.toString());
        } catch (Exception e) {
            return String.format("A problem occurred when creating Music [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editMusic(@PathVariable("id") Integer id, @RequestBody Music music) {
        try {
            service.update(id, music);
            return String.format("Music [%s] successfully updated", id);
        } catch (Exception e) {
            return String.format("A problem occurred when updating Music [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") Integer id) {
        try {
            musicRepository.delete(id);
            return String.format("Music [%s] successfully deleted", id); // uses the delete() method inherited from CrudRepository
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Music [%s]", e.getMessage());
        }
    }
}
