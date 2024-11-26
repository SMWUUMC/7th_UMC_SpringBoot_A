package study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.handler.MemberHandler;
import study.apiPayload.exception.handler.StoreHandler;
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
    public Review createReview(ReviewRequestDTO.PostReviewDTO request) {
        Review review = ReviewConverter.toReview(request);
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(()->new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(()->new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        review.setStore(store);
        review.setMember(member);
        return reviewRepository.save(review);
    }
}
