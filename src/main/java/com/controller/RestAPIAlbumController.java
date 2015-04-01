package com.controller;

import com.bean.Album;
import com.dao.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAlbum(@RequestBody Album album) {
        try {
            albumRepository.save(album);
            return String.format("Album [%s] successfully created", album.toString());
        } catch (Exception e) {
            return String.format("A problem occurred when creating Artist [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editAlbum(@PathVariable("id") Integer id, @RequestBody Album a) {
        try {
            Album album = albumRepository.findOne(id);
            album.setTitle(a.getTitle());
            album.setArtist(a.getArtist());
            albumRepository.save(album);
            return String.format("Album [%s] successfully updated", id);
        } catch (Exception e) {
            return String.format("A problem occurred when updating Album [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") Integer id) {
        try {
            albumRepository.delete(id);
            return String.format("Album [%s] successfully deleted", id); // uses the delete() method inherited from CrudRepository
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Album [%s]", e.getMessage());
        }
    }
}
