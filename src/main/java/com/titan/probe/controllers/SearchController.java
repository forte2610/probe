/*
 *  SearchController
 *
 *  Author: 1412093
 *
 *  This controller handles the system's primary operation: search
 */

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
        if (!query.equals("")) {
            if (page == null) {
                String keyword = "";

                keyword = query.trim();
                String[] itemsKeyword = keyword.split(" ");
                String temp = "";
                for (String s : itemsKeyword) {
                    temp += s + " ";
                }
                keyword = temp.substring(0, temp.length() - 1);

                Crawler crawler = new Crawler(keyword);
                ResultObject resultObject = crawler.process();
                ResultDetails details = new ResultDetails(resultObject.getKeyword(),
                        resultObject.getCount(), resultObject.getPages(), resultObject.getTimeElapsed());
                PagedListHolder<Product> pagedProductList = new PagedListHolder<Product>();
                pagedProductList.setSource(resultObject.getResultList());
                pagedProductList.setPageSize(10);
                req.getSession().setAttribute("resultList", pagedProductList);
                req.getSession().setAttribute("resultDetails", details);
                System.out.println("Finished after " + details.getTimeElapsed() + " milliseconds.");
            } else if (page.equals("next")) {
                PagedListHolder<Product> pagedProductList = (PagedListHolder<Product>) req.getSession().getAttribute("resultList");
                if (!pagedProductList.isLastPage())
                return "redirect:/search?q=" + query + "&p=" + (pagedProductList.getPage() + 1);
            } else if (page.equals("prev")) {
                PagedListHolder<Product> pagedProductList = (PagedListHolder<Product>) req.getSession().getAttribute("resultList");
                if (!pagedProductList.isFirstPage())
                return "redirect:/search?q=" + query + "&p=" + (pagedProductList.getPage() - 1);
            } else {
                PagedListHolder<Product> pagedProductList = (PagedListHolder<Product>) req.getSession().getAttribute("resultList");
                int pageIndex = Integer.parseInt(page);
                pagedProductList.setPage(pageIndex);
            }
            return "results";
        }
        else return "redirect:/";
    }
}
