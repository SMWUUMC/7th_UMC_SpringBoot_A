package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.dto.ReviewResponseDTO;

public class ReviewConverter {
    public static ReviewResponseDTO toDTO(Review review) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .content(review.getBody()) // body 필드 필요
                .score(review.getScore())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }
}

