package umc.spring.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Mission;

public interface MissionQueryService {

    Page<Mission> getMissionsByStore(Long storeId, int page);
}

