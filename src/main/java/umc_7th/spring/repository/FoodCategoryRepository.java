package umc_7th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
