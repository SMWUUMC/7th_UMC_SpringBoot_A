package study.service.ReviewService;

import study.domain.Review;
import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDTO.PostReviewDTO request);
}
