package study.converter;

import study.domain.Member;
import study.domain.Review;
import study.domain.Store;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;

public class ReviewConverter {
    public static Review toReview(ReviewRequestDTO.JoinDTO request){
        return Review.builder()
                .score(request.getScore())
                .body(request.getBody())
                .store(null)
                .member(null)
                .build();
    }

    public static ReviewResponseDTO.JoinResultDTO toReviewResponseDTO(Review review){
        return ReviewResponseDTO.JoinResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
