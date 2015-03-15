package com.controller;

import com.bean.Music;
import com.dao.IMusicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by melkir on 13/03/15.
 */

@Controller
public class MusicController {

    @Autowired
    IMusicDAO repository;

    /**
     * Test repository
     * @return music string
     */
    @RequestMapping("/music")
    @ResponseBody
    public String music() {
        repository.save(new Music("Track1", "Marshall", "Eminem"));
        repository.save(new Music("Track2", "Marshall", "Eminem"));
        repository.save(new Music("Track3", "Marshall", "Eminem"));
        Music music = repository.findOne(1L);
        return music.toString();
    }

    @RequestMapping(value = "/music/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String findMusic(@RequestParam("name") String name) {
/*        final List<Music> list = repository.findByTitleOrAlbumOrArtist(name);
        return list.toString();*/
        return "hello";
    }

    @RequestMapping(value = "/music/list", method = RequestMethod.GET)
    public String afficher(Model model) {
        final List<Music> list = repository.findAll();
        model.addAttribute("listMusics", list);
        return "tabmusic";
    }

}
