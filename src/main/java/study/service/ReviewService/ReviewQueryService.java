package study.service.ReviewService;

import org.springframework.data.domain.Page;
import study.domain.Review;

public interface ReviewQueryService {
    Page<Review> getReviewList(Long storeId, Integer page);
}
