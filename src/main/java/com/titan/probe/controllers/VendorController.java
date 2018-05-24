/*
 *  VendorController
 *
 *  Author: 1412093
 *
 *  This controller handles URLs related vendor oriented functionality
 */

package com.titan.probe.controllers;

import com.titan.probe.models.Review;
import com.titan.probe.models.User;
import com.titan.probe.models.Vendor;
import com.titan.probe.services.ReviewService;
import com.titan.probe.services.UserService;
import com.titan.probe.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VendorController {
    @Autowired
    VendorService vendorService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @ModelAttribute("newReview")
    public Review getReview() {
        return new Review();
    }

    @RequestMapping(value="/vendors", method = RequestMethod.GET)
    public ModelAndView viewVendors(){
        List<Vendor> vendors = new ArrayList<Vendor>();
        Iterable<Vendor> vendorIterable = vendorService.findAll();
        vendorIterable.forEach(vendors::add);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vendors");
        modelAndView.addObject("vendors", vendors);
        return modelAndView;
    }

    @RequestMapping(value="/vendor-details", method = RequestMethod.GET)
    public String viewVendorDetails(HttpServletRequest req, @RequestParam(value="id") int vendorId,
                                          @RequestParam(value = "p", required = false) String page, Principal user){
        Vendor currentVendor = vendorService.findVendorById(vendorId).get();
        if (user != null) {
            req.getSession().setAttribute("is_loggedin", true);
            req.getSession().setAttribute("user_name",user.getName());
        }
        else req.getSession().setAttribute("is_loggedin", false);
        req.getSession().setAttribute("vendor", currentVendor);
        double reviewScore = 0;
        if (page == null) {
            List<Review> allReviews = vendorService.getReviews(vendorId);
            int reviewCount = allReviews.size();
            for (int i = 0; i < reviewCount; i++) {
                reviewScore += allReviews.get(i).getScore();
            }
            if (reviewCount > 0) reviewScore = Math.round((reviewScore / reviewCount) * 2) / 2.0;
            else reviewScore = 2.5;
            PagedListHolder<Review> pagedReviewList = new PagedListHolder<>();
            pagedReviewList.setSource(allReviews);
            pagedReviewList.setPageSize(20);
            req.getSession().setAttribute("reviewList", pagedReviewList);
            req.getSession().setAttribute("currentPage", pagedReviewList.getPage());
            req.getSession().setAttribute("review_score", reviewScore);
            req.getSession().setAttribute("review_count", reviewCount);
        } else if (page.equals("next")) {
            PagedListHolder<Review> pagedReviewList = (PagedListHolder<Review>) req.getSession().getAttribute("reviewList");
            if (!pagedReviewList.isLastPage())
                return "redirect:/vendor-details?id=" + vendorId + "&p=" + (pagedReviewList.getPage() + 1);
        } else if (page.equals("prev")) {
            PagedListHolder<Review> pagedReviewList = (PagedListHolder<Review>) req.getSession().getAttribute("reviewList");
            if (!pagedReviewList.isFirstPage())
                return "redirect:/vendor-details?id=" + vendorId + "&p=" + (pagedReviewList.getPage() - 1);
        } else {
            PagedListHolder<Review> pagedReviewList = (PagedListHolder<Review>) req.getSession().getAttribute("reviewList");
            int pageIndex = Integer.parseInt(page);
            pagedReviewList.setPage(pageIndex);
            req.getSession().setAttribute("currentPage", pagedReviewList.getPage());
        }
        return "vendor_details";
    }

    @RequestMapping(value="/submit-review/{id}", method = RequestMethod.GET)
    public ModelAndView submitNewReview(@PathVariable(value="id") int vendorId, @ModelAttribute("newReview") Review review){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submit_review");
        modelAndView.addObject("vendorid", vendorId);
        return modelAndView;
    }

    @RequestMapping(value="/submit-review/{id}", method = RequestMethod.POST)
    public String submitNewReview(@PathVariable(value="id") int vendorId, @Valid @ModelAttribute("newReview") Review review, BindingResult result){
        Vendor currentVendor = vendorService.findVendorById(vendorId).get();
        review.setVendor(currentVendor);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userService.findUserByUsername(currentPrincipalName);
        review.setAuthor(currentUser);
        reviewService.saveReview(review);
        return "redirect:/vendor-details/" + vendorId;
    }
}
