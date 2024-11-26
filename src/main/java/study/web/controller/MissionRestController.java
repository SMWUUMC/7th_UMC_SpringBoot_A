package study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.apiPayload.ApiResponse;
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
    public ApiResponse<MissionResponseDTO.changeMissionStatusResultDto> changeMissionStatus(@PathVariable("mission_id") Long missionId,
                                                                                            @RequestBody @Valid MissionRequestDTO.changeMissionStatusDto request){
        request.setMissionId(missionId);
        MemberMission mission = missionCommandService.changeMissionStatus(request);
        return ApiResponse.onSuccess(MemberMissionConverter.)
    }
}
