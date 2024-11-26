package umc_7th.spring.service.StoreService;

import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Review;
import umc_7th.spring.web.dto.StoreDTO.StoreRequestDTO;

public interface StoreCommandService {

    // 2. 가게에 리뷰 추가하기 API
    Review createReview (Long storeId, StoreRequestDTO.createReviewDTO request);

    // 3. 가게에 미션 추가하기 API
    Mission createMission (Long storeId, StoreRequestDTO.createMissionDTO request);
}
