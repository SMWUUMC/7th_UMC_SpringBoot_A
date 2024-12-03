package umc.spring.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Mission;

public interface MissionQueryService {

    Page<Mission> getMissionsByStore(Long storeId, int page);

    // 사용자가 진행 중인 미션 목록을 조회
    Page<Mission> getMissionsInProgressByUserId(Long userId, int page);
}

