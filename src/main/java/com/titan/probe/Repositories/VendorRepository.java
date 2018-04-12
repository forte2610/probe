package com.titan.probe.Repositories;

import com.titan.probe.models.Review;
import com.titan.probe.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    @Query("SELECT r FROM Review r WHERE r.vendor = ?1")
    public List<Review> findReviewsForVendor(Vendor vendor);
}
