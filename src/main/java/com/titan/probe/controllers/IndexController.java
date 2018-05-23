/*
 *  IndexController
 *
 *  Author: 1412093
 *
 *  This controller handles URLs related to the home page
 */

package com.titan.probe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String Homepage() {
        return "home";
    }
}
