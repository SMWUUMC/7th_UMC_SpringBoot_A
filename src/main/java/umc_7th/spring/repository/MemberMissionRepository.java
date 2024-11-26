package umc_7th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
