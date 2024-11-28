package umc_7th.spring.converter;

import org.springframework.data.domain.Page;
import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Review;
import umc_7th.spring.web.dto.StoreDTO.StoreRequestDTO;
import umc_7th.spring.web.dto.StoreDTO.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Review toReview (StoreRequestDTO.createReviewDTO request) { // 2. 가게에 리뷰 추가하기 API
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }
    public static StoreResponseDTO.createReviewResultDTO createReviewResultDTO(Review review) {
        return StoreResponseDTO.createReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Mission toMission (StoreRequestDTO.createMissionDTO request) { // 3. 가게에 미션 추가하기 API
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }
    public static StoreResponseDTO.createMissionResultDTO createMissionResultDTO(Mission mission) {
        return StoreResponseDTO.createMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
