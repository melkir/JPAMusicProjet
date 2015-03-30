package com.controller;

import com.bean.Album;
import com.bean.Artist;
import com.bean.Music;
import com.dao.AlbumRepository;
import com.dao.ArtistRepository;
import com.dao.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melkir on 13/03/15.
 */

@Controller
@RequestMapping(value = "/music")
public class MusicController {

    @Autowired
    MusicRepository musicRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String show(Model model) {
        final List<Music> list = musicRepository.findAll();
        model.addAttribute("listMusics", list);
        return "tabmusic";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String find(@PathVariable String name, Model model) {
        final List<Music> list = musicRepository.findByTitleOrAlbumOrArtist(name);
        model.addAttribute("listMusics", list);
        return "tabmusic";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateById(@PathVariable("id") Integer id, Model model) {
        Music music = musicRepository.findOne(id);
        model.addAttribute("music", music);
        return "formUpdate";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String musicUpdate(@PathVariable("id") Integer id, @ModelAttribute Music music) {
        try {
            checkIfArtistOrAlbumAlreadyExist(music);
            music = musicRepository.update(id, music);
            musicRepository.save(music);
            return String.format("Music [%s] successfully edited", id);
        } catch (Exception e) {
            return String.format("A problem occurred when editing Music [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteById(@PathVariable("id") Integer id) {
        try {
            musicRepository.delete(id);
            return String.format("Music [%s] successfully deleted", id); // uses the delete() method inherited from CrudRepository
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Music [%s]", e.getMessage());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String musicForm(Model model) {
        model.addAttribute("music", new Music());
        return "form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String musicSubmit(@ModelAttribute Music music, Model model) {
        checkIfArtistOrAlbumAlreadyExist(music);
        Artist artist = artistRepository.findOne(music.getArtist().getId());
        artist.addProduct(music);
        Album album = albumRepository.findOne(music.getAlbum().getId());
        album.setArtist(music.getArtist());
        musicRepository.save(music);
        model.addAttribute("music", music);
        return "result";
    }

    private void checkIfArtistOrAlbumAlreadyExist(Music music) {
        // Check if artist name already exist
        Artist artist = artistRepository.findByName(music.getArtist().getName());
        if(artist != null) music.setArtist(artist);
        else artistRepository.save(music.getArtist());
        // Check if album name already exist
        Album album = albumRepository.findByTitle(music.getAlbum().getTitle());
        if(album != null) music.setAlbum(album);
        else albumRepository.save(music.getAlbum());
    }

}
