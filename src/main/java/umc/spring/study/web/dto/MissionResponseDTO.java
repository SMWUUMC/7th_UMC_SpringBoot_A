package umc.spring.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.study.domain.enums.MissionStatus;

import java.time.LocalDate;


public class MissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor

    public static class CreateMissionResultDto{
        private Long missionId;
        private Long memberId;
        private MissionStatus status;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDto {
        private Long missionId;
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
        private Long storeId;
    }

}

