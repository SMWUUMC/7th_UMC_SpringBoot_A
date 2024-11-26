package study.service.MissionService;

import study.domain.Mission;
import study.domain.mapping.MemberMission;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

public interface MissionCommandService {
    //가게에 미션 추가하기 API
    public Mission assignMissionToStore(MissionRequestDTO.AssignMissionToStoreDto request);
    //가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
    public MemberMission changeMissionStatus(MissionRequestDTO.changeMissionStatusDto request);
}
