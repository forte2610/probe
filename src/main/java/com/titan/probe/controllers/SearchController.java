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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String Search(HttpServletRequest req, @RequestParam("q") String query,
                         @RequestParam(value = "p", required = false) String page) {
        if (page == null) {
            String keyword = "";

            if (query != null) {
                keyword = query.trim();
                String[] itemsKeyword = keyword.split(" ");
                String temp = "";
                for (String s : itemsKeyword) {
                    temp += s + " ";
                }
                keyword = temp.substring(0, temp.length() - 1);
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
        }
        else if (page.equals("next")) {
            PagedListHolder<Product> pagedProductList = (PagedListHolder<Product>)req.getSession().getAttribute("resultList");
            pagedProductList.nextPage();
        }
        else if (page.equals("prev")) {
            PagedListHolder<Product> pagedProductList = (PagedListHolder<Product>)req.getSession().getAttribute("resultList");
            pagedProductList.previousPage();
        }
        return "results";
    }
}
