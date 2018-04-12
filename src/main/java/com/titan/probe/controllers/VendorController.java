package com.titan.probe.controllers;

import com.titan.probe.models.Review;
import com.titan.probe.models.User;
import com.titan.probe.models.Vendor;
import com.titan.probe.services.ReviewService;
import com.titan.probe.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VendorController {
    @Autowired
    VendorService vendorService;

    @Autowired
    ReviewService reviewService;

    @ModelAttribute("newReview")
    public Review getReview() {
        Review review = new Review();
        return review;
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
    public ModelAndView viewVendorDetails(@PathVariable(value="id") int vendorId){
        Vendor currentVendor = vendorService.findVendorById(vendorId).get();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vendor_details");
        modelAndView.addObject("vendor", currentVendor);
        return modelAndView;
    }

    @RequestMapping(value="/submit-review", method = RequestMethod.GET)
    public ModelAndView submitNewReview(@ModelAttribute("newReview") Review review){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submit_review");
        return modelAndView;
    }

    @RequestMapping(value="/submit-review/{id}", method = RequestMethod.POST)
    public ModelAndView submitNewReview(@PathVariable(value="id") int vendorId, @Valid @ModelAttribute("newReview") Review review, BindingResult result){
        Vendor currentVendor = vendorService.findVendorById(vendorId).get();
        review.setVendor(currentVendor);
        reviewService.saveReview(review);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submit_review");
        return modelAndView;
    }
}
