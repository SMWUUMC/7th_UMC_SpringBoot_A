package study.converter;

import study.domain.Mission;
import study.domain.mapping.MemberMission;
import study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MissionResponseDTO.changeMissionStatusResultDto toChangeMissionStatusResultDto(MemberMission memberMission) {
        return MissionResponseDTO.changeMissionStatusResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
