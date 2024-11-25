package study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class MissionRequestDTO {
    // 개인 미션 조회
    @Getter
    @AllArgsConstructor
    public static class FindIndividualMissionDto{
        Long memberId;
        String missionStatus;
        Long regionId;
    }

}
