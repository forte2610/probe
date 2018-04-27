package com.titan.probe.controllers;

import com.titan.probe.helpers.Crawler;
import com.titan.probe.models.Product;
import com.titan.probe.models.ResultDetails;
import com.titan.probe.models.ResultObject;
import org.json.JSONObject;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String Search(HttpServletRequest req) {
        String keyword = "";

        if (req.getParameter("query") != null) {
            keyword = req.getParameter("query").trim();
            String[] itemsKeyword = keyword.split(" ");
            String temp = "";
            for (String s : itemsKeyword) {
                temp += s + " ";
            }
            keyword = temp;
        }

        Crawler crawler = new Crawler(keyword);
        ResultObject resultObject = crawler.process();
        ResultDetails details = new ResultDetails(resultObject.getKeyword(),
        resultObject.getCount(), resultObject.getPages(), resultObject.getTimeElapsed());
        PagedListHolder<Product> pagedProductList = new PagedListHolder<Product>();
        pagedProductList.setSource(resultObject.getResultList());
        pagedProductList.setPageSize(10);
        req.getSession().setAttribute("resultList", pagedProductList);
        req.getSession().setAttribute("resultDetails", details);
        return "results";
    }
}
