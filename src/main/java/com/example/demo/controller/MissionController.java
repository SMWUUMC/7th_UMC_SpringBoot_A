package com.example.demo.controller;

import com.example.demo.base.ApiResponse;
import com.example.demo.converter.MemberMissionConverter;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.dto.MissionRequestDTO;
import com.example.demo.dto.MissionResponseDTO;
import com.example.demo.service.MissionService.MissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {
    private final MissionService missionCommandService;

    @PostMapping("/{mission_id}/challenging")
    public ApiResponse<MissionResponseDTO.changeMissionStatusResultDto> changeMissionStatus(@PathVariable("mission_id") Long missionId,
                                                                                            @RequestBody @Valid MissionRequestDTO.changeMissionStatusDto request){
        request.setMissionId(missionId);
        MemberMission mission = missionCommandService.changeMissionStatus(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChangeMissionStatusResultDto(mission));
    }
}
