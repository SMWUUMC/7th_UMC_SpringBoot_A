package umc.spring.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.domain.Mission;
import umc.spring.study.service.MissionService.MissionCommandService;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionConverter missionConverter;  // MissionConverter 주입

    // 미션 도전하기
    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDTO.CreateMissionResultDto> challengeMission(
            @RequestBody @Valid MissionRequestDTO.ChallengeMissionDto request) {

        // MissionRequestDTO -> Mission 엔티티로 변환
        Mission mission = missionConverter.toMission(request);  // 인스턴스 메서드로 호출

        // 미션 도전 서비스 호출
        Mission result = missionCommandService.challengeMission(mission);

        // Mission 엔티티를 DTO로 변환하여 응답
        return ApiResponse.onSuccess(missionConverter.toCreateMissionResultDto(result));  // 인스턴스 메서드로 호출
    }
}
