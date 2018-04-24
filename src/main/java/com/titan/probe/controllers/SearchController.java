package com.titan.probe.controllers;

import com.titan.probe.parsers.TGDDParser;
import com.titan.probe.parsers.VienthongAParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SearchController {
    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String Search(WebRequest req) {
        VienthongAParser testProcessor = new VienthongAParser(req.getParameter("query"));
        System.out.println(req.getParameter("query"));
        testProcessor.process();
        return "results";
    }
}
