package study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.apiPayload.ApiResponse;
import study.converter.MemberMissionConverter;
import study.domain.mapping.MemberMission;
import study.service.MissionService.MissionCommandService;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/{mission_id}/challenging")
    @Operation(summary = "미션 도전 API",description = "Member와 Mission을 다대다 연관관계로 맺어주는 POST 메소드")
    public ApiResponse<MissionResponseDTO.changeMissionStatusResultDto> changeMissionStatus(@PathVariable("mission_id") Long missionId,
                                                                                            @RequestBody @Valid MissionRequestDTO.changeMissionStatusDto request){
        request.setMissionId(missionId);
        MemberMission mission = missionCommandService.changeMissionStatus(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChangeMissionStatusResultDto(mission));
    }
}
