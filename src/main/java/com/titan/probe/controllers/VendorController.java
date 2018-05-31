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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private PageRequest gotoPage(int page) {
        PageRequest request = PageRequest.of(page, 20);
        return request;
    }

    @RequestMapping(value = "/vendors", method = RequestMethod.GET)
    public ModelAndView viewVendors() {
        List<Vendor> vendors = new ArrayList<Vendor>();
        Iterable<Vendor> vendorIterable = vendorService.findAll();
        vendorIterable.forEach(vendors::add);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vendors");
        modelAndView.addObject("vendors", vendors);
        return modelAndView;
    }

    @RequestMapping(value = "/vendor-details", method = RequestMethod.GET)
    public String viewVendorDetails(HttpServletRequest req, @RequestParam(value = "id") int vendorId,
                                    @RequestParam(value = "p", required = false) String page, Principal user) {
        Vendor currentVendor = vendorService.findVendorById(vendorId).get();
        if (user != null) {
            req.getSession().setAttribute("is_loggedin", true);
            req.getSession().setAttribute("user_name", user.getName());
        } else req.getSession().setAttribute("is_loggedin", false);
        req.getSession().setAttribute("vendor", currentVendor);
        double reviewScore;
        if (page == null) {
            Page<Review> pagedReviews = vendorService.getReviews(vendorId, gotoPage(0));
            long reviewCount = pagedReviews.getTotalElements();
            long pageCount = pagedReviews.getTotalPages();
            req.getSession().setAttribute("review_list", pagedReviews.getContent());
            req.getSession().setAttribute("current_page", 0);
            if (reviewCount > 0) {
                reviewScore = vendorService.getAverageScore(vendorId);
                req.getSession().setAttribute("review_score", reviewScore);
            }
            else req.getSession().setAttribute("review_score", 0);
            req.getSession().setAttribute("review_count", reviewCount);
            req.getSession().setAttribute("page_count", pageCount);
        } else if (page.equals("next")) {
            int pageNumber = (int)req.getSession().getAttribute("current_page");
            return "redirect:/vendor-details?id=" + vendorId + "&p=" + (pageNumber + 1);
        } else if (page.equals("prev")) {
            int pageNumber = (int)req.getSession().getAttribute("current_page");
            return "redirect:/vendor-details?id=" + vendorId + "&p=" + (pageNumber - 1);
        } else {
            int pageIndex = Integer.parseInt(page);
            Page<Review> pagedReviews = vendorService.getReviews(vendorId, gotoPage(pageIndex));
            req.getSession().setAttribute("review_list", pagedReviews.getContent());
            req.getSession().setAttribute("current_page", pageIndex);
        }
        return "vendor_details";
    }

    @RequestMapping(value = "/submit-review", method = RequestMethod.POST)
    public String submitNewReview(@RequestParam(value = "id") int vendorId, @Valid @ModelAttribute("newReview") Review review, BindingResult result) {
        Vendor currentVendor = vendorService.findVendorById(vendorId).get();
        review.setVendor(currentVendor);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userService.findUserByUsername(currentPrincipalName);
        review.setAuthor(currentUser);
        reviewService.saveReview(review);
        return "redirect:/vendor-details?id=" + vendorId;
    }
}
