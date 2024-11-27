package study.converter;

import study.domain.Mission;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

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
                .missionSpec(dto.getMissionSpec())
                .reward(dto.getReward())
                .deadline(dto.getDeadline())
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
