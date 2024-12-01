package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewRequestDTO {
    public static class PostReviewDTO {
        @NotNull
        private Long storeId;

        @NotNull
        private Long memberId;  // Add this field if it doesn't exist

        @Size(min = 1, max = 255)
        private String reviewContent;

        // Getters and Setters
        public Long getStoreId() {
            return storeId;
        }

        public void setStoreId(Long storeId) {
            this.storeId = storeId;
        }

        public Long getMemberId() {
            return memberId;
        }

        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

        public String getReviewContent() {
            return reviewContent;
        }

        public void setReviewContent(String reviewContent) {
            this.reviewContent = reviewContent;
        }
    }
}
