package study.service.ReviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.domain.Review;

public interface ReviewQueryService {
    Page<Review> getReviewListOfStore(Long storeId, Integer page);
    Page<Review> getReviewListOfMember(Long memberId, Pageable pageable);
}
