package com.titan.probe.Repositories;

import com.titan.probe.models.Review;
import com.titan.probe.models.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r WHERE r.vendor = ?1 ORDER BY r.timestamp DESC")
    Page<Review> findAllReviewsOfVendor(Vendor vendor, Pageable pageable);
}
