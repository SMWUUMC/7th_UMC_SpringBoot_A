package study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain.Member;
import study.domain.Review;
import study.domain.Store;
import study.repository.MemberRepository.MemberRepository;
import study.repository.ReviewRepository.ReviewRepository;
import study.repository.StoreRepository.StoreRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Review> getReviewListOfStore(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    @Override
    public Page<Review> getReviewListOfMember(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId).get();
        return reviewRepository.findAllByMember(member, PageRequest.of(pageable.getPageNumber(), 10));
    }
}
