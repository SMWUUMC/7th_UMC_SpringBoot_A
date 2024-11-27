package study.converter;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import study.domain.Review;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;

import java.util.stream.Collectors;
import java.util.*;
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
    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewList){
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewPreviewDTO).collect(Collectors.toList());
        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
