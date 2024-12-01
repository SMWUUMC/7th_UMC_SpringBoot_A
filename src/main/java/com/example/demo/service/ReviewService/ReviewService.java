package com.example.demo.service.ReviewService;


import com.example.demo.domain.Review;
import com.example.demo.dto.ReviewRequestDTO;

public interface ReviewService {
    Review createReview(ReviewRequestDTO.PostReviewDTO request);
}
