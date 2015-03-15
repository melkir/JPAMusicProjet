package com.controller;

/**
 * Created by melkir on 13/03/15.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    /**
     * Simple request mapping hello world
     * @return String Hello World
     */
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    /**
     * Use mvc model view with Thymeleaf and URI
     * @param name /greeting?name=Melkir
     * @param model the attribute to pass to the view
     * @return Hello World with a view
     */
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
