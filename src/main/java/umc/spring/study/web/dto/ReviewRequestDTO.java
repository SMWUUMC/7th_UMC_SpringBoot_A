package umc.spring.study.web.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    @Setter
    public static class CreateReviewDto {

        @NotNull
        Long storeId;
        @NotNull
        @Min(value = 1)
        @Max(value = 5)
        Float rating;

        @NotBlank
        @Size(min = 10, max = 1000)
        String content;

        List<String> images;
    }
}
