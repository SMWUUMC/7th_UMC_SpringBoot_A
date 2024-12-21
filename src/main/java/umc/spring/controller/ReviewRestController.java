package umc.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.dto.ReviewResponseDTO;
import umc.spring.service.ReviewService.ReviewService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.validator.CheckPageValidator;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;
    public ApiResponse<Page<ReviewResponseDTO>> getUserReviews(
            @PathVariable Long userId, // userId를 경로 변수로 받음
            @CheckPage @RequestParam(name = "page") Integer page) {
        PageRequest pageRequest = PageRequest.of(CheckPageValidator.toZeroBasedPage(page), 10);
        Page<Review> reviews = reviewService.getMyReviews(userId, pageRequest);
        Page<ReviewResponseDTO> reviewResponseDTOs = reviews.map(ReviewConverter::toDTO);
        return ApiResponse.onSuccess(reviewResponseDTOs);
    }
}
