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
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String Search(HttpServletRequest req, @RequestParam("q") String query,
                         @RequestParam(value = "p", required = false) String page,
                         @RequestParam(name = "filter", defaultValue = "all") String filter,
                         @RequestParam(name = "price", defaultValue = "all") String price,
                        @RequestParam(name = "vendor", defaultValue = "all") String vendor) {
        if (!query.equals("")) {
            if (page == null){
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
                List<Product> resultList = resultObject.getResultList();
                if (!filter.equals("all")){
                    resultList = resultList.stream()
                            .filter((item)->item.getType().getName().equals(filter)).collect(Collectors.toList());
                }
                if (!vendor.equals("all")){
                    resultList = resultList.stream()
                            .filter((item)->item.getVendorName().equals(vendor)).collect(Collectors.toList());
                }
                if (!price.equals("all")){
                    String prices[] = price.split("-");
                    int price1 = Integer.parseInt(prices[0]);
                    int price2 = Integer.parseInt(prices[1]);
                    resultList = resultList.stream()
                            .filter((item)->(item.getPrice() < price2)&&(item.getPrice()>=price1)).collect(Collectors.toList());
                }

                req.getSession().setAttribute("filter", filter);
                req.getSession().setAttribute("vendor", vendor);
                req.getSession().setAttribute("price", price);

                pagedProductList.setSource(resultList);
                pagedProductList.setPageSize(10);
                req.getSession().setAttribute("resultList", pagedProductList);
                req.getSession().setAttribute("resultDetails", details);

                req.getSession().setAttribute("currentPage", pagedProductList.getPage());
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
                req.getSession().setAttribute("currentPage", pagedProductList.getPage());
            }
            return "results";
        }
        else return "redirect:/";
    }
}
