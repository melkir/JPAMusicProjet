package com.controller;

import com.bean.Album;
import com.bean.Artist;
import com.bean.Music;
import com.dao.AlbumRepository;
import com.dao.ArtistRepository;
import com.dao.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melkir on 13/03/15.
 */

@RestController
@RequestMapping(value = "/api/music")
public class RestAPIController {

    @Autowired
    MusicRepository musicRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
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
            checkIfArtistOrAlbumAlreadyExist(music);
            musicRepository.save(music);
            return String.format("Music [%s] successfully created", music.toString());
        } catch (Exception e) {
            return String.format("A problem occurred when creating Music [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editMusic(@PathVariable("id") Integer id, @RequestBody Music music) {
        try {
            checkIfArtistOrAlbumAlreadyExist(music);
            music = musicRepository.update(id, music);
            musicRepository.save(music);
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

    private void checkIfArtistOrAlbumAlreadyExist(Music music) {
        // Check if artist name already exist
        Artist artist = artistRepository.findByName(music.getArtist().getName());
        if (artist != null) music.setArtist(artist);
        else artistRepository.save(music.getArtist());
        // Check if album name already exist
        Album album = albumRepository.findByTitle(music.getAlbum().getTitle());
        if (album != null) music.setAlbum(album);
        else albumRepository.save(music.getAlbum());
    }

}
