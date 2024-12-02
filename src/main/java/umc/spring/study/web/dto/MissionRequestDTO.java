package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

public class MissionRequestDTO {
    @Getter
    @Setter
    public static class ChallengeMissionDto{
        @NotNull
        private Long missionId;

        @NotNull
        private Long storeId;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListRequestDto {
        private Long storeId;
        private int page;
    }
}
