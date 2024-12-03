package umc.spring.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.domain.Mission;
import umc.spring.study.service.MissionService.MissionCommandService;
import umc.spring.study.service.MissionService.MissionQueryService;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;
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

    // 9주차 미션2 : 특정 가게의 미션 목록 조회
    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게에 할당된 미션 목록을 페이징 처리하여 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "토큰이 필요합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "토큰 형식 오류", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID", required = true),
            @Parameter(name = "page", description = "조회할 페이지 번호", required = true)
    })
    public ApiResponse<List<MissionResponseDTO.MissionListDto>> getMissionsByStore(@RequestParam(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {

        // 특정 가게의 미션 목록을 페이징 처리하여 조회
        Page<Mission> missionPage = missionQueryService.getMissionsByStore(storeId, page);

        // 조회된 미션 목록을 DTO로 변환하여 응답
        return ApiResponse.onSuccess(missionConverter.toMissionListDtoList(missionPage.getContent()));
    }

    // 9주차 미션3 : 내가 진행 중인 미션 목록 조회
    @GetMapping("/progress")
    @Operation(summary = "내가 진행 중인 미션 목록 조회", description = "사용자가 진행 중인 미션 목록을 페이징 처리하여 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "토큰이 필요합니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "토큰 형식 오류", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID", required = true),
            @Parameter(name = "page", description = "조회할 페이지 번호", required = true)
    })
    public ApiResponse<List<MissionResponseDTO.MissionListDto>> getMyProgressMissions(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "page") Integer page) {

        // 사용자의 진행 중인 미션 목록을 페이징 처리하여 조회
        Page<Mission> missionPage = missionQueryService.getMissionsInProgressByUserId(userId, page);

        // 조회된 미션 목록을 DTO로 변환하여 응답
        return ApiResponse.onSuccess(missionConverter.toMissionListDtoList(missionPage.getContent()));
    }
}
