package com.example.demo.controller;

import com.example.demo.base.ApiResponse;
import com.example.demo.converter.MissionConverter;
import com.example.demo.converter.ReviewConverter;
import com.example.demo.domain.Mission;
import com.example.demo.domain.Review;
import com.example.demo.dto.MissionRequestDTO;
import com.example.demo.dto.MissionResponseDTO;
import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.service.MissionService.MissionService;
import com.example.demo.service.ReviewService.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final ReviewService reviewCommandService;
    private final MissionService missionCommandService;
    @PostMapping("/{store_id}/reviews")
    public ApiResponse<ReviewResponseDTO.PostReviewResultDTO> postReview(
            @PathVariable("store_id") Long storeId,
            @RequestBody @Valid ReviewRequestDTO.PostReviewDTO request) {
        request.setStoreId(storeId); // 이 시점에서 유효성 검사 실행
        Review review = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toPostReviewResultDTO(review));
    }


    @PostMapping("/{store_id}/missions")
    public ApiResponse<MissionResponseDTO.AssignMissionToStoreResultDto> postMission(@PathVariable("store_id") Long storeId,
                                                                                     @RequestBody @Valid MissionRequestDTO.AssignMissionToStoreDto request) {
        request.setStoreId(storeId);
        Mission mission = missionCommandService.assignMissionToStore(request);
        return ApiResponse.onSuccess(MissionConverter.toAssignMissionToStoreResultDto(mission));
    }
}
