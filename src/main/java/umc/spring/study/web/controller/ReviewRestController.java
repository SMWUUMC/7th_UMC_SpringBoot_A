package umc.spring.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.ReviewConverter;
import umc.spring.study.domain.Review;
import umc.spring.study.service.ReviewService.ReviewCommandService;
import umc.spring.study.web.dto.ReviewRequestDTO;
import umc.spring.study.web.dto.ReviewResponseDTO;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    //리뷰 생성 API
    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateReviewDto request) {
        // 요청 DTO -> 엔티티 변환 및 저장
        Review review = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDTO(review));
    }
}

