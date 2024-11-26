package study.converter;

import study.domain.Review;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;

public class ReviewConverter {
    public static Review toReview(ReviewRequestDTO.PostReviewDTO request){
        return Review.builder()
                .score(request.getScore())
                .body(request.getBody())
                .store(null)
                .member(null)
                .build();
    }

    public static ReviewResponseDTO.PostReviewResultDTO toPostReviewResultDTO(Review review){
        return ReviewResponseDTO.PostReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
