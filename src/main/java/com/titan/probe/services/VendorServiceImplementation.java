package com.titan.probe.services;

import com.titan.probe.Repositories.VendorRepository;
import com.titan.probe.models.Review;
import com.titan.probe.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImplementation implements VendorService {
    @Autowired
    @Qualifier("vendorRepository")
    VendorRepository vendorRepository;

    @Override
    public Iterable<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Optional<Vendor> findVendorById(int id) {
        return vendorRepository.findById(id);
    }

    @Override
    public List<Review> getReviews(int vendorId) {
        Vendor vendor = findVendorById(vendorId).get();
        return vendorRepository.findReviewsForVendor(vendor);
    }
}
