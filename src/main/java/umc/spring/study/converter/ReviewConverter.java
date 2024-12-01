package umc.spring.study.converter;

import umc.spring.study.domain.Review;
import umc.spring.study.web.dto.ReviewRequestDTO;
import umc.spring.study.web.dto.ReviewResponseDTO;

public class ReviewConverter {

    // ReviewRequestDTO.CreateReviewDto -> Review 엔티티 변환
    public static Review toReview(ReviewRequestDTO.CreateReviewDto request) {
        return Review.builder()
                .body(request.getContent())
                .score(request.getRating())
                .build();
    }

    // Review 엔티티 -> ReviewResponseDTO.ReviewResultDTO 변환
    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review) {
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt()) // BaseEntity의 createdAt
                .rating(review.getScore())
                .content(review.getBody())
                .build();
    }
}

