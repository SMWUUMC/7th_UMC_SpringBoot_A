package umc.spring.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Review;
import umc.spring.study.repository.MemberRepository;
import umc.spring.study.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Review> getReviewsByUser(Long userId, Integer page) {
        // Member 조회 예외처리 부분 제거
        Member member = memberRepository.findById(userId).get();

        // Member를 기준으로 리뷰 목록을 페이징 처리하여 반환
        return reviewRepository.findAllByMember(member, PageRequest.of(page - 1, 10));
    }
}



