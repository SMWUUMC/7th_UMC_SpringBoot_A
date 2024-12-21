package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Review;

public interface ReviewService {
    Page<Review> getMyReviews(Long memberId, Pageable pageable);
}
