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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value="/vendor-details/{id}", method = RequestMethod.GET)
    public ModelAndView viewVendorDetails(@PathVariable(value="id") int vendorId, Principal user){
        Vendor currentVendor = vendorService.findVendorById(vendorId).get();
        List<Review> allReviews = vendorService.getReviews(vendorId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vendor_details");
        if (user != null) {
            modelAndView.addObject("is_loggedin", true);
            modelAndView.addObject("user_name",user.getName());
        }
        else modelAndView.addObject("is_loggedin", false);
        modelAndView.addObject("vendor", currentVendor);
        modelAndView.addObject("reviews", allReviews);
        double reviewScore = 0;
        int reviewCount = allReviews.size();
        for (int i = 0; i < reviewCount; i++) {
            reviewScore += allReviews.get(i).getScore();
        }
        if (reviewCount > 0) reviewScore = Math.round((reviewScore / reviewCount) * 2) / 2.0;
        else reviewScore = 2.5;
        modelAndView.addObject("review_score", reviewScore);
        modelAndView.addObject("review_count", reviewCount);
        return modelAndView;
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
