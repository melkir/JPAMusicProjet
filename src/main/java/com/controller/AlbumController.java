package com.controller;

import com.bean.Album;
import com.dao.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by melkir on 02/04/15.
 */
@Controller
@RequestMapping(value = "/album")
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String show(Model model) {
        final List<Album> list = albumRepository.findAll();
        model.addAttribute("listAlbums", list);
        model.addAttribute("nbAlbum", albumRepository.count());
        return "tabalbum";
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable int id, Model model) {
        Album album = albumRepository.findOne(id);
        model.addAttribute("album", album);
        return "album";
    }

}
