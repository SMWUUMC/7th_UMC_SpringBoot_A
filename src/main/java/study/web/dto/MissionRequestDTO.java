package study.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import study.validation.annotation.ExistMember;
import study.validation.annotation.ExistMission;
import study.validation.annotation.ExistStore;

import java.time.LocalDateTime;

public class MissionRequestDTO {
    // 개인 미션 조회
    @Getter
    @AllArgsConstructor
    public static class FindIndividualMissionDto{
        Long memberId;
        String missionStatus;
        Long regionId;
    }

    //가게에 미션 추가하기 API
    @Getter
    @Setter
    @AllArgsConstructor
    public static class AssignMissionToStoreDto {
        @ExistStore
        Long storeId;
        Integer reward;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime deadline;

        String missionSpec;
    }

    //미션 상태 변경 API
    @Getter
    @Setter
    @AllArgsConstructor
    public static class changeMissionStatusDto {
        @ExistMember
        Long memberId;
        @ExistMission
        Long missionId;
        String missionStatus;
    }
}
