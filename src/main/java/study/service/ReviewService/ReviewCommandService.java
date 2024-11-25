package study.service.ReviewService;

import study.web.dto.ReviewRequestDTO;
import study.web.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    ReviewResponseDTO.JoinResultDTO createReview(ReviewRequestDTO.JoinDTO request);
}
