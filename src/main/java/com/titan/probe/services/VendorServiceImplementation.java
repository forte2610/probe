package com.titan.probe.services;

import com.titan.probe.Repositories.ReviewRepository;
import com.titan.probe.Repositories.VendorRepository;
import com.titan.probe.models.Review;
import com.titan.probe.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImplementation implements VendorService {
    @Autowired
    @Qualifier("vendorRepository")
    VendorRepository vendorRepository;

    @Autowired
    @Qualifier("reviewRepository")
    ReviewRepository reviewRepository;

    @Override
    public Iterable<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Optional<Vendor> findVendorById(int id) {
        return vendorRepository.findById(id);
    }

    @Override
    public Page<Review> getReviews(int vendorId, Pageable pageRequest) {
        Vendor vendor = findVendorById(vendorId).get();
        return reviewRepository.findAllReviewsOfVendor(vendor, pageRequest);
    }

    @Override
    public double getAverageScore(int vendorId) {
        Vendor vendor = findVendorById(vendorId).get();
        double result = vendorRepository.getAverageScore(vendor);
        return Math.round(result * 2) / 2.0;
    }
}
