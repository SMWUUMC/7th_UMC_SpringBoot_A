package umc.spring.study.web.dto;

import lombok.*;

import java.time.LocalDateTime;

public class ReviewResponseDTO {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewResultDTO {

        Long reviewId;
        LocalDateTime createdAt;
        String content;
        Float rating;

//        public static ReviewResultDTO success(Long reviewId, LocalDateTime createdAt) {
//            return new ReviewResultDTO(reviewId, createdAt);
//        }
    }
}