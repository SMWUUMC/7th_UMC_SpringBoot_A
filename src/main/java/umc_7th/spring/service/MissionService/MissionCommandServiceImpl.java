package umc_7th.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc_7th.spring.converter.MissionConverter;
import umc_7th.spring.domain.mapping.MemberMission;
import umc_7th.spring.repository.MemberMissionRepository;
import umc_7th.spring.repository.MemberRepository;
import umc_7th.spring.repository.MissionRepository;
import umc_7th.spring.web.dto.MissionDTO.MissionRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
    @Override
    public MemberMission createMemberMission (Long memberId, Long missionId, MissionRequestDTO.createMemberMissionDTO request) {
        MemberMission memberMission = MissionConverter.toMemberMission(request);
        memberMission.setMission(missionRepository.findById(missionId).get());
        memberMission.setMember(memberRepository.findById(memberId).get());

        return memberMissionRepository.save(memberMission);
    }
}
