package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class MissionRequestDTO {
    @Getter
    @Setter
    public static class ChallengeMissionDto{
        @NotNull
        private Long missionId;

        @NotNull
        private Long storeId;

    }
}
