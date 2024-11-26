package umc_7th.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc_7th.spring.apiPayload.ApiResponse;
import umc_7th.spring.converter.MissionConverter;
import umc_7th.spring.domain.mapping.MemberMission;
import umc_7th.spring.service.MissionService.MissionCommandService;
import umc_7th.spring.web.dto.MissionDTO.MissionRequestDTO;
import umc_7th.spring.web.dto.MissionDTO.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{missionId}") // 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
    public ApiResponse<MissionResponseDTO.createMemberMissionResultDTO> createMemberMission(@RequestBody @Valid MissionRequestDTO.createMemberMissionDTO request,
                                                                                            @RequestParam(name = "memberId") Long memberId,
                                                                                            @PathVariable(name = "missionId") Long missionId) {
        MemberMission memberMission = missionCommandService.createMemberMission(memberId, missionId, request);

        return ApiResponse.onSuccess(MissionConverter.createMemberMissionResultDTO(memberMission));

    }
}
