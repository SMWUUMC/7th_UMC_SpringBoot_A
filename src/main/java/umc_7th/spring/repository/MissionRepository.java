package umc_7th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
