package umc_7th.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc_7th.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findByMemberId(Long memberId, PageRequest pageRequest); // 3. 내가 진행 중인 미션 목록 조회하기 API
}
