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
import study.converter.MemberMissionConverter;
import study.converter.MissionConverter;
import study.converter.ReviewConverter;
import study.converter.StoreConverter;
import study.domain.*;
import study.service.MissionService.MissionCommandService;
import study.service.MissionService.MissionQueryService;
import study.service.ReviewService.ReviewCommandService;
import study.service.ReviewService.ReviewQueryService;
import study.service.StoreService.StoreCommandService;
import study.validation.annotation.CheckPage;
import study.validation.annotation.ExistStore;
import study.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;
    private final StoreCommandService storeCommandService;
    private final ReviewQueryService reviewQueryService;
    private final MissionQueryService missionQueryService;

    @GetMapping("/{store_id}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistStore @PathVariable("store_id") Long storeId,@RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = reviewQueryService.getReviewListOfStore(storeId,page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreviewListDTO(reviewList));
    }

    @GetMapping("/{store_id}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionDetailListDto> getMissionList(@ExistStore @PathVariable("store_id") Long storeId, @CheckPage Pageable pageable) {
        Page<Mission> page = missionQueryService.findMissionListByStore(storeId, pageable);
        return ApiResponse.onSuccess(MissionConverter.toMissionDetailListDto(page));
    }

    @PostMapping("/")
    @Operation(summary = "가게 추가 API",description = "Region에 Store 추가하는 POST 메소드")
    public ApiResponse<StoreResponseDTO.AddStoreResultDTO> postStore(@RequestBody @Valid StoreRequestDTO.AddStoreDTO request){
        Store store = storeCommandService.addStore(request);
        return  ApiResponse.onSuccess(StoreConverter.toAddStoreResultDTO(store));
    }

    @PostMapping("/{store_id}/reviews/post")
    @Operation(summary = "가게의 리뷰 추가 api",description = "Store에 Review 추가하는 POST 메소드")
    public ApiResponse<ReviewResponseDTO.PostReviewResultDTO> postReview(@ExistStore @PathVariable("store_id") Long storeId,
                                                                         @RequestBody @Valid ReviewRequestDTO.PostReviewDTO request) {
        request.setStoreId(storeId);
        Review review = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toPostReviewResultDTO(review));
    }

    @PostMapping("/{store_id}/missions")
    @Operation(summary = "가게의 미션 추가 API",description = "Store에 Mission 추가하는 POST 메소드")
    public ApiResponse<MissionResponseDTO.AssignMissionToStoreResultDto> postMission(@ExistStore @PathVariable("store_id") Long storeId,
                                                                                     @RequestBody @Valid MissionRequestDTO.AssignMissionToStoreDto request) {
        request.setStoreId(storeId);
        Mission mission = missionCommandService.assignMissionToStore(request);
        return ApiResponse.onSuccess(MissionConverter.toAssignMissionToStoreResultDto(mission));
    }
}
