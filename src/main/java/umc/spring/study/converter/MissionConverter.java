package umc.spring.study.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionConverter {

    private final StoreRepository storeRepository;

    @Autowired
    public MissionConverter(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Mission toMission(MissionRequestDTO.ChallengeMissionDto request) {
        // storeId로 Store 엔티티를 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + request.getStoreId()));

        // Mission 엔티티로 변환
        return Mission.builder()
                .id(request.getMissionId())
                .store(store)
                .build();
    }

    public MissionRequestDTO.ChallengeMissionDto toMissionRequestDTO(Mission mission) {
        MissionRequestDTO.ChallengeMissionDto dto = new MissionRequestDTO.ChallengeMissionDto();
        dto.setMissionId(mission.getId());
        dto.setStoreId(mission.getStore().getId());

        return dto;
    }

    public MissionResponseDTO.CreateMissionResultDto toCreateMissionResultDto(Mission mission) {
        // MemberMission 리스트가 비어 있는지 확인
        if (mission.getMemberMissionList().isEmpty()) {
            // 비어 있을 경우 예외를 던지거나 기본값을 반환할 수 있음
            throw new RuntimeException("No member mission found for mission with id: " + mission.getId());
        }

        // 리스트가 비어 있지 않으면 첫 번째 요소를 사용
        MemberMission memberMission = mission.getMemberMissionList().get(0);  // 예시로 첫 번째 멤버 미션 사용

        return MissionResponseDTO.CreateMissionResultDto.builder()
                .missionId(mission.getId())
                .memberId(memberMission.getMember().getId())
                .status(memberMission.getStatus())
                .build();
    }

    // 9주차 미션2 : 특정가게의 미션 조회
    // 개별 미션을 MissionListDto로 변환
    public MissionResponseDTO.MissionListDto toMissionListDto(Mission mission) {
        return MissionResponseDTO.MissionListDto.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    // 미션 목록을 MissionListDto 목록으로 변환
    public List<MissionResponseDTO.MissionListDto> toMissionListDtoList(List<Mission> missionList) {
        return missionList.stream()
                .map(this::toMissionListDto)
                .collect(Collectors.toList());
    }

}
