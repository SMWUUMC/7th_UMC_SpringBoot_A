package study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReviewRequestDTO {
    @Getter
    @AllArgsConstructor
    public static class JoinDTO{
        Long memberId;
        Long storeId;
        String body;
        Float score;
    }
}
