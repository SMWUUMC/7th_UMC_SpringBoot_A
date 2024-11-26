package umc_7th.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc_7th.spring.apiPayload.ApiResponse;
import umc_7th.spring.converter.StoreConverter;
import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Review;
import umc_7th.spring.service.StoreService.StoreCommandService;
import umc_7th.spring.web.dto.StoreDTO.StoreRequestDTO;
import umc_7th.spring.web.dto.StoreDTO.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController  {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/review") // 2. 가게에 리뷰 추가하기 API
    public ApiResponse<StoreResponseDTO.createReviewResultDTO> createReview (@RequestBody @Valid StoreRequestDTO.createReviewDTO request,
                                                                             @PathVariable(name = "storeId") Long storeId) {
        Review review = storeCommandService.createReview(storeId, request);

        return ApiResponse.onSuccess(StoreConverter.createReviewResultDTO(review));
    }
    @PostMapping("/{storeId}/mission") // 3. 가게에 미션 추가하기 API
    public ApiResponse<StoreResponseDTO.createMissionResultDTO> createMission (@RequestBody @Valid StoreRequestDTO.createMissionDTO request,
                                                                               @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.createMission(storeId, request);

        return ApiResponse.onSuccess(StoreConverter.createMissionResultDTO(mission));
    }
}
