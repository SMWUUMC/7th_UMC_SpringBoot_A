package umc.spring.study.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Review;

public interface ReviewQueryService {
    Page<Review> getReviewsByUser(Long userId, Integer page);
}
