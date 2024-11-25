package study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.converter.ReviewConverter;
import study.domain.Member;
import study.domain.Review;
import study.domain.Store;
import study.repository.MemberRepository.MemberRepository;
import study.repository.ReviewRepository.ReviewRepository;
import study.repository.StoreRepository.StoreRepository;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.JoinResultDTO createReview(ReviewRequestDTO.JoinDTO request) {
        Review review = ReviewConverter.toReview(request);
        //예외처리 추후 추가
        Store store = storeRepository.findById(request.getStoreId()).orElse(null);
        Member member = memberRepository.findById(request.getMemberId()).orElse(null);
        review.setStore(store);
        review.setMember(member);
        reviewRepository.save(review);
        return ReviewConverter.toReviewResponseDTO(review);
    }
}
