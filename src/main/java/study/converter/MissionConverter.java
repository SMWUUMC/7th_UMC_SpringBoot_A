package study.converter;

import study.domain.Mission;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;
import java.util.*;
import java.util.stream.Collectors;

public class MissionConverter {
    public static List<MissionResponseDTO.FindIndividualMissionResultDto> toMissionResultDto(List<Mission> missionList){
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

}
