package umc_7th.spring.converter;

import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Review;
import umc_7th.spring.web.dto.StoreDTO.StoreRequestDTO;
import umc_7th.spring.web.dto.StoreDTO.StoreResponseDTO;

import java.time.LocalDateTime;

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
}
