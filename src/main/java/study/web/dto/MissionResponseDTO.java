package study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {
    // 개인 미션 조회
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindIndividualMissionResultDto {
        Long missionId;
        Long storeId;
        Integer reward;
        String missionSpec;
        String storeName;
        LocalDateTime deadline;
    }
}
