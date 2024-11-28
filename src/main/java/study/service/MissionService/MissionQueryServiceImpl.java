package study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.apiPayload.code.status.ErrorStatus;
import study.apiPayload.exception.handler.StoreHandler;
import study.domain.Mission;
import study.domain.Store;
import study.domain.enums.MissionStatus;
import study.domain.mapping.MemberMission;
import study.repository.MissionRepository.MissionRepository;

import org.springframework.data.domain.Pageable;
import study.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Page<MemberMission> findIndividualMissionByMissionStatus(Long memberId, MissionStatus missionStatus, Pageable pageable) {
        if(missionStatus == MissionStatus.CHALLENGING) {
            return missionRepository.findIndividualChallengingMission(memberId, pageable);
        }
        else if(missionStatus == MissionStatus.COMPLETE){
            return missionRepository.findIndividualCompleteMission(memberId, pageable);
        }
        else return null;
    }

    @Override
    public Page<Mission> findClaimableMission(Long memberId, Long regionId, Pageable pageable){
        return missionRepository.findIndividualClaimableMission(memberId, regionId, pageable);
    }

    @Override
    public Page<Mission> findMissionListByStore(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        return missionRepository.findAllByStore(store, pageable);
    }

    @Override
    public boolean isValid(Long id) {
        return missionRepository.existsById(id);
    }
}
