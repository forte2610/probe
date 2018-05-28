package com.titan.probe.services;

import com.titan.probe.models.Review;
import com.titan.probe.models.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    public Iterable<Vendor> findAll();
    public Optional<Vendor> findVendorById(int id);
    public Page<Review> getReviews(int vendorId, Pageable pageRequest);
}
