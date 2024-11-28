package umc_7th.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc_7th.spring.apiPayload.ApiResponse;
import umc_7th.spring.converter.StoreConverter;
import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Review;
import umc_7th.spring.service.StoreService.StoreCommandService;
import umc_7th.spring.service.StoreService.StoreQueryService;
import umc_7th.spring.validation.annotation.ExistStore;
import umc_7th.spring.web.dto.StoreDTO.StoreRequestDTO;
import umc_7th.spring.web.dto.StoreDTO.StoreResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController  {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/{storeId}/review") // 2. 가게에 리뷰 추가하기 API
    public ApiResponse<StoreResponseDTO.createReviewResultDTO> createReview (@RequestBody @Valid StoreRequestDTO.createReviewDTO request,
                                                                             @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Review review = storeCommandService.createReview(storeId, request);

        return ApiResponse.onSuccess(StoreConverter.createReviewResultDTO(review));
    }
    @PostMapping("/{storeId}/mission") // 3. 가게에 미션 추가하기 API
    public ApiResponse<StoreResponseDTO.createMissionResultDTO> createMission (@RequestBody @Valid StoreRequestDTO.createMissionDTO request,
                                                                               @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);

        return ApiResponse.onSuccess(StoreConverter.createMissionResultDTO(mission));
    }
    @GetMapping("/{storeId}/reviews")
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
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,@RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }
}
