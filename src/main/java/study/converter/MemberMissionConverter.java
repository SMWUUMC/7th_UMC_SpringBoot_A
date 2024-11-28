package study.converter;

import org.springframework.data.domain.Page;
import study.domain.Mission;
import study.domain.mapping.MemberMission;
import study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.*;
public class MemberMissionConverter {
    public static MissionResponseDTO.changeMissionStatusResultDto toChangeMissionStatusResultDto(MemberMission memberMission) {
        return MissionResponseDTO.changeMissionStatusResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.MissionDetailDto toMissionDetailDto(MemberMission memberMission) {
        return MissionResponseDTO.MissionDetailDto.builder()
                .missionId(memberMission.getId())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .deadline(memberMission.getMission().getDeadline())
                .reward(memberMission.getMission().getReward())
                .storeId(memberMission.getMission().getStore().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionDetailListDto toMissionDetailListDto(Page<MemberMission> memberMissionList) {
        List<MissionResponseDTO.MissionDetailDto> missionDetailDtoList = memberMissionList.stream()
                .map(MemberMissionConverter::toMissionDetailDto).collect(Collectors.toList());
        return MissionResponseDTO.MissionDetailListDto.builder()
                .isFirst(memberMissionList.isFirst())
                .isLast(memberMissionList.isLast())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(missionDetailDtoList.size())
                .missionList(missionDetailDtoList)
                .build();
    }
}
