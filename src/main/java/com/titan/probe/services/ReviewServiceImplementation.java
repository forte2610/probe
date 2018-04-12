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
        reviewRepository.save(review);
    }
}
