package umc.spring.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.MemberRepository;
import umc.spring.study.repository.ReviewRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.CreateReviewDto request) {
        // 유효한 사용자 확인 -> memberid라는 변수값 가져오는데 문제 생겨서 일단 주석처리
//        Member member = memberRepository.findById(request.getMemberId())
//                .orElseThrow(() -> new RuntimeException("User not found"));

        // 유효한 매장 확인 (request에서 storeId 추출)
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // Review 객체 생성
        Review review = Review.builder()
                .store(store)
                .content(request.getContent())
                .rating(request.getRating())
                .build();


        // Review 저장
        return reviewRepository.save(review);
    }
}

