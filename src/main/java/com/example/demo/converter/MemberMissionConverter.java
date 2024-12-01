package com.example.demo.converter;

import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MissionResponseDTO.changeMissionStatusResultDto toChangeMissionStatusResultDto(MemberMission memberMission) {
        return MissionResponseDTO.changeMissionStatusResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
