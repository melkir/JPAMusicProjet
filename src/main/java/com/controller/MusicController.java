package com.controller;

import com.bean.Music;
import com.dao.MusicRepository;
import com.services.MusicManagement;
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
    MusicManagement service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(@RequestParam(value = "search", defaultValue = "") String search, Model model) {
        final List<Music> list;
        if (search == null || search.equals("")) list = musicRepository.findAll();
        else list = musicRepository.findByTitleOrAlbumOrArtist(search);
        model.addAttribute("listMusics", list);
        return "tabmusic";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    @ResponseBody
    public String loadFromJson() {
        try {
            service.loadMusicFromJson();
            return "Playlist successfully loaded";
        } catch (Exception e) {
            return "A problem occurred when uploading playlist";
        }
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
            music = musicRepository.update(id, music);
            service.save(music);
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
        service.save(music);
        model.addAttribute("music", music);
        return "result";
    }

}
