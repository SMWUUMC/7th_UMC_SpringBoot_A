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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.ReviewConverter;
import umc.spring.study.domain.Review;
import umc.spring.study.service.ReviewService.ReviewCommandService;
import umc.spring.study.service.ReviewService.ReviewQueryService;
import umc.spring.study.web.dto.ReviewRequestDTO;
import umc.spring.study.web.dto.ReviewResponseDTO;

@RestController
@RequestMapping("/reviews")
@Validated
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    //리뷰 생성 API
    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateReviewDto request) {
        // 요청 DTO -> 엔티티 변환 및 저장
        Review review = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDTO(review));
    }

    // 9주차 미션1 : 리뷰 조회 API

    @GetMapping("/list")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 페이징 처리하여 조회하는 API입니다.")
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
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@RequestParam(name = "userId") Long userId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviews = reviewQueryService.getReviewsByUser(userId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviews.getContent(), reviews.getTotalElements(), reviews.getTotalPages(), reviews.isFirst(), reviews.isLast()));
    }

}

