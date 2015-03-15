package com.controller;

import com.bean.Music;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by melkir on 13/03/15.
 */

@RestController
public class ApiController {

    /**
     * Restful api
     * @return json music
     */
    @RequestMapping("/music/rest")
    public Music musicRest() {
        return new Music("Titre", "Album", "Artist");
    }

}
