package study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import study.domain.Mission;
import study.domain.mapping.MemberMission;

import org.springframework.data.domain.Pageable;

public interface MissionRepositoryCustom {
    Page<MemberMission> findIndividualChallengingMission(Long memberId, Pageable pageable);
    Page<MemberMission> findIndividualCompleteMission(Long memberId, Pageable pageable);
    Page<Mission> findIndividualClaimableMission(Long memberId, Long regionId, Pageable pageable);
}
