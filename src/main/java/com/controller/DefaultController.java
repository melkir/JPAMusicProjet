package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by melkir on 13/04/15.
 */

@Controller
public class DefaultController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String greeting() {
        return "index";
    }

}
