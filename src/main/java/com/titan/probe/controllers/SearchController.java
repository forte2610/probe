package com.titan.probe.controllers;

import com.titan.probe.helpers.Crawler;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SearchController {
    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String Search(WebRequest req) {
        String keyword = "";

        if (req.getParameter("key") != null) {
            keyword = req.getParameter("key").trim();
            String[] itemsKeyword = keyword.split(" ");
            String temp = "";
            for (String s : itemsKeyword) {
                temp += s + " ";
            }
            keyword = temp;
        }

        Crawler crawler = new Crawler(keyword);
        JSONObject resultObject = crawler.process();
        return "results";
    }
}
