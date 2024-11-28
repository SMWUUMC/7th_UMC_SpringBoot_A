package study.converter;

import org.springframework.data.domain.Page;
import study.domain.Mission;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.*;
public class MissionConverter {
    public static List<MissionResponseDTO.MissionDetailDto> toFindIndividualMissionResultDto(List<Mission> missionList){
        return missionList.stream()
                .map(mission ->
                        MissionResponseDTO.MissionDetailDto.builder()
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

    public static MissionResponseDTO.MissionDetailDto toMissionDetailDto(Mission mission){
        return MissionResponseDTO.MissionDetailDto.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .storeName(mission.getStore().getName())
                .storeId(mission.getStore().getId())
                .build();
    }

    public static MissionResponseDTO.MissionDetailListDto toMissionDetailListDto(Page<Mission> missionList){
        List<MissionResponseDTO.MissionDetailDto> missionDetailDtoList = missionList.stream()
                .map(MissionConverter::toMissionDetailDto).collect(Collectors.toList());

        return MissionResponseDTO.MissionDetailListDto.builder()
                .missionList(missionDetailDtoList)
                .listSize(missionDetailDtoList.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }
}
