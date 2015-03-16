package com.controller;

import com.bean.Music;
import com.dao.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
