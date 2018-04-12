package com.titan.probe.services;

import com.titan.probe.models.Review;
import com.titan.probe.models.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    public Iterable<Vendor> findAll();
    public Optional<Vendor> findVendorById(int id);
    public List<Review> getReviews(int vendorId);
}
