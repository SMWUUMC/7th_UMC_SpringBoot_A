package study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.apiPayload.ApiResponse;
import study.converter.MissionConverter;
import study.converter.ReviewConverter;
import study.domain.*;
import study.service.MissionService.MissionCommandService;
import study.service.ReviewService.ReviewCommandService;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;
    @PostMapping("/{store_id}/reviews")
    public ApiResponse<ReviewResponseDTO.PostReviewResultDTO> postReview(@PathVariable("store_id") Long storeId,
                                                                         @RequestBody @Valid ReviewRequestDTO.PostReviewDTO request) {
        request.setStoreId(storeId);
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
