package com.controller;

import com.bean.Music;
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
    MusicRepository service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String show(Model model) {
        final List<Music> list = service.findAll();
        model.addAttribute("listMusics", list);
        return "tabmusic";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String find(@PathVariable String name, Model model) {
        final List<Music> list = service.findByTitleOrAlbumOrArtist(name);
        model.addAttribute("listMusics", list);
        return "tabmusic";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateById(@PathVariable("id") Integer id, Model model) {
        Music music = service.findOne(id);
        model.addAttribute("music", music);
        return "formUpdate";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String musicUpdate(@PathVariable("id") Integer id, @ModelAttribute Music music, Model model) {
        Music musicNew = service.update(id, music);
        service.save(musicNew);
        model.addAttribute("music", music);
        return "result";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteById(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
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
