package umc_7th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
