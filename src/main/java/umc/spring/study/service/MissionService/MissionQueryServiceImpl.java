package umc.spring.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.study.domain.Mission;
import umc.spring.study.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> getMissionsByStore(Long storeId, int page) {
        // 특정 가게의 미션 목록을 페이징 처리하여 반환
        return missionRepository.findByStoreId(storeId, PageRequest.of(page - 1,  10));
    }

    @Override
    public Page<Mission> getMissionsInProgressByUserId(Long userId, int page) {
        // 사용자 ID에 맞는 진행 중인 미션을 페이징 처리하여 반환
        return missionRepository.findMissionsInProgressByUserId(userId, PageRequest.of(page - 1, 10));
    }
}
