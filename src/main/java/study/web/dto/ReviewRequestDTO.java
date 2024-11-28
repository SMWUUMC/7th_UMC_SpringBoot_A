package study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import study.validation.annotation.ExistMember;
import study.validation.annotation.ExistStore;

public class ReviewRequestDTO {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class PostReviewDTO {
        @ExistMember
        Long memberId;
        @ExistStore
        Long storeId;
        String body;
        Float score;
    }

}
