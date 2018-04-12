package com.titan.probe.services;

import com.titan.probe.Repositories.ReviewRepository;
import com.titan.probe.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("reviewService")
public class ReviewServiceImplementation implements ReviewService {
    @Autowired
    @Qualifier("reviewRepository")
    ReviewRepository reviewRepository;

    @Override
    public void saveReview(Review review) {
        review.setTimestamp(new Timestamp(System.currentTimeMillis()));
        System.out.println("Vendor: " + review.getVendor().getName());
        System.out.println("Author: " + review.getAuthor().getUsername());
        System.out.println("Score: " + review.getScore());
        System.out.println("Comment: " + review.getContent());
        reviewRepository.save(review);
    }
}
