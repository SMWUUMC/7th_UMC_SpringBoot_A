package umc_7th.spring.web.dto.StoreDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class StoreRequestDTO {

    @Getter
    public static class createReviewDTO { // 2. 가게에 리뷰 추가하기 API
        @NotBlank
        private String body;
        @NotNull
        private float score;
    }

    @Getter
    public static class createMissionDTO { // 3. 가게에 미션 추가하기 API
        @NotNull
        private Integer reward;
        @NotNull
        private LocalDate deadline;
        @NotBlank
        private String missionSpec;
    }
}
