package umc.spring.study.converter;

import umc.spring.study.domain.Review;
import umc.spring.study.web.dto.ReviewRequestDTO;
import umc.spring.study.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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


    // 9주차 미션1
    // 리뷰 엔티티를 DTO로 변환
    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())  // 작성자 닉네임
                .score(review.getScore())  // 평점
                .body(review.getBody())  // 내용
                .createdAt(review.getCreatedAt().toLocalDate())  // 생성일
                .build();
    }

    // 리뷰 목록을 DTO로 변환
    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(List<Review> reviewList, long totalElements, int totalPages, boolean isFirst, boolean isLast) {
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOS = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewPreViewDTOS)
                .listSize(reviewList.size())
                .totalElements(totalElements)
                .totalPage(totalPages)
                .isFirst(isFirst)
                .isLast(isLast)
                .build();
    }
}

