package study.service.MissionService;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.converter.MissionConverter;
import study.domain.*;
import study.domain.enums.MissionStatus;
import study.domain.mapping.MemberMission;
import study.repository.MemberRepository.MemberRepository;
import study.repository.MissionRepository.MissionRepository;
import study.repository.StoreRepository.StoreRepository;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

@Transactional
@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final JPAQueryFactory jpaQueryFactory;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository

    @Override
    public Mission assignMissionToStore(MissionRequestDTO.AssignMissionToStoreDto request) {
        Mission newMission = MissionConverter.toMission(request);
        Store store = storeRepository.findByIdFetchJoinRegion(request.getStoreId());
        newMission.setStore(store);
        newMission.setRegion(store.getRegion());
        return missionRepository.save(newMission);
    }

    @Override
    public MemberMission changeMissionStatus(MissionRequestDTO.changeMissionStatusDto request) {
        MemberMission memberMission = MemberMission.builder().build();
        memberMission.setStatus(MissionStatus.CHALLENGING);
        memberMission.setMission(missionRepository.findById(request.getMissionId()).orElse(null));
        memberMission.setMember(memberRepository.findById(request.getMemberId()).orElse(null));
        return memberMissionRepository.save(memberMission);
    }

}
