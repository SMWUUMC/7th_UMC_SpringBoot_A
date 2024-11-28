package study.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.domain.Mission;
import study.domain.enums.MissionStatus;
import study.domain.mapping.MemberMission;

public interface MissionQueryService {
    Page<MemberMission> findIndividualMissionByMissionStatus(Long memberId, MissionStatus missionStatus, Pageable pageable);
    Page<Mission> findClaimableMission(Long memberId, Long regionId, Pageable pageable);
    Page<Mission> findMissionListByStore(Long storeId, Pageable pageable);
    boolean isValid(Long id);
}
