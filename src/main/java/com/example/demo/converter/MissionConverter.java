package com.example.demo.converter;


import com.example.demo.domain.Mission;
import com.example.demo.dto.MissionRequestDTO;
import com.example.demo.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MissionConverter {
    public static List<MissionResponseDTO.FindIndividualMissionResultDto> toFindIndividualMissionResultDto(List<Mission> missionList){
        return missionList.stream()
                .map(mission ->
                        MissionResponseDTO.FindIndividualMissionResultDto.builder()
                                .missionId(mission.getId())
                                .missionSpec(mission.getMissionSpec())
                                .reward(mission.getReward())
                                .storeId(mission.getStore().getId())
                                .storeName(mission.getStore().getName())
                                .build())
                .collect(Collectors.toList());
    }

    public static Mission toMission(MissionRequestDTO.AssignMissionToStoreDto dto){
        return Mission.builder()
                .store(null)
                .region(null)
                .build();
    }
    public static MissionResponseDTO.AssignMissionToStoreResultDto toAssignMissionToStoreResultDto(Mission mission){
        return MissionResponseDTO.AssignMissionToStoreResultDto.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
