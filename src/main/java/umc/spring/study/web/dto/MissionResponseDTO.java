package umc.spring.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.study.domain.enums.MissionStatus;


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
}

