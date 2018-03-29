package com.titan.probe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String Homepage() {
        return "home";
    }

    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String Search() {
        return "results";
    }
}
