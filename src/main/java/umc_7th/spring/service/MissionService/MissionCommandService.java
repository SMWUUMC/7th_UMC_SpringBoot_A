package umc_7th.spring.service.MissionService;

import umc_7th.spring.domain.mapping.MemberMission;
import umc_7th.spring.web.dto.MissionDTO.MissionRequestDTO;

public interface MissionCommandService {

    // 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
    MemberMission createMemberMission (Long missionId, MissionRequestDTO.createMemberMissionDTO request) ;
}
