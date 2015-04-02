package com.controller;

import com.bean.Album;
import com.dao.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String show() {
        final List<Album> list = albumRepository.findAll();
        return list.toString();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable int id) {
        Album album = albumRepository.findOne(id);
        return album.toString();
    }

}
