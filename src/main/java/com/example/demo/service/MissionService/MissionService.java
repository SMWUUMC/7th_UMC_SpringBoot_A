package com.example.demo.service.MissionService;

import com.example.demo.domain.Mission;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.dto.MissionRequestDTO;

public interface MissionService {
    Mission assignMissionToStore(MissionRequestDTO.AssignMissionToStoreDto request);

    MemberMission changeMissionStatus(MissionRequestDTO.changeMissionStatusDto request);
}
