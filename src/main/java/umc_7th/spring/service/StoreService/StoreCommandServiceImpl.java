package umc_7th.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc_7th.spring.converter.StoreConverter;
import umc_7th.spring.domain.Mission;
import umc_7th.spring.domain.Review;
import umc_7th.spring.repository.MissionRepository;
import umc_7th.spring.repository.ReviewRepository;
import umc_7th.spring.repository.StoreRepository.StoreRepository;
import umc_7th.spring.web.dto.StoreDTO.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Review createReview (Long storeId, StoreRequestDTO.createReviewDTO request) { // 2. 가게에 리뷰 추가하기 API
        Review review = StoreConverter.toReview(request);
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }
    @Override
    public Mission createMission (Long storeId, StoreRequestDTO.createMissionDTO request) { // 3. 가게에 미션 추가하기 API
        Mission mission = StoreConverter.toMission(request);
        mission.setStore(storeRepository.findById(storeId).get());

        return missionRepository.save(mission);
    }
}
