package umc_7th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
