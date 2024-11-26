package umc_7th.spring.web.dto.MissionDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc_7th.spring.domain.Member;
import umc_7th.spring.domain.enums.MissionStatus;

public class MissionRequestDTO {

    @Getter
    public static class createMemberMissionDTO { // 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
        @NotNull
        private MissionStatus status;
    }
}
