package study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import study.apiPayload.ApiResponse;
import study.converter.MemberConverter;
import study.converter.MemberMissionConverter;
import study.converter.MissionConverter;
import study.converter.ReviewConverter;
import study.domain.Member;
import study.domain.Mission;
import study.domain.Review;
import study.domain.enums.MissionStatus;
import study.domain.mapping.MemberMission;
import study.service.MemberService.MemberCommandService;
import study.service.MissionService.MissionQueryService;
import study.service.ReviewService.ReviewQueryService;
import study.validation.annotation.CheckPage;
import study.validation.annotation.ExistMember;
import study.validation.annotation.ExistStore;
import study.web.dto.MemberRequestDTO;
import study.web.dto.MemberResponseDTO;
import study.web.dto.MissionResponseDTO;
import study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final ReviewQueryService reviewQueryService;
    private final MissionQueryService missionQueryService;
    private final MemberCommandService memberCommandService;

    @GetMapping("/{member_id}/missions/{mission_status}")
    @Operation(summary = "멤버 미션 목록 조회 API",description = "미션들의 목록을 조회하는 페이징 API. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "missionStatus", description = "path variable / Challenging, Complete 중 하나")
    })
    public ApiResponse<MissionResponseDTO.MissionDetailListDto> getMissionList(@ExistMember @PathVariable("member_id") Long memberId, @PathVariable("mission_status") String missionStatus,
                                                                               @CheckPage Pageable pageable) {
        Page<MemberMission> page = missionQueryService.findIndividualMissionByMissionStatus(memberId, MissionStatus.valueOf(missionStatus.toUpperCase()), pageable);
        return ApiResponse.onSuccess(MemberMissionConverter.toMissionDetailListDto(page));
    }

    @GetMapping("/{member_id}/reviews")
    @Operation(summary = "개인 리뷰 목록 조회 API", description = "멤버가 작성한 리뷰들의 목록을 조회하는 페이징 API.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "Common200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistMember @PathVariable("member_id") Long memberId,
                                                                             @CheckPage Pageable pageable) {
        Page<Review> page = reviewQueryService.getReviewListOfMember(memberId, pageable);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreviewListDTO(page));
    }

    @PostMapping("/")
    @Operation(summary = "멤버 가입 API",description = "멤버 가입 POST 메소드")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}
