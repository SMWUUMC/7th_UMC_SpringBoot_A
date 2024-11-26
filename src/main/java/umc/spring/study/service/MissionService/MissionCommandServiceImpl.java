package umc.spring.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.study.domain.Mission;
import umc.spring.study.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Mission challengeMission(Mission request) {
        // 미션을 찾기
        Mission mission = missionRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다"));

        return missionRepository.save(mission);
    }
}
