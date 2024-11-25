package study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain.enums.MissionStatus;
import study.repository.MemberRepository.MemberRepository;
import study.repository.MissionRepository.MissionRepositoryImpl;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepositoryImpl missionRepository;
    @Override
    public List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualMissionByMissionStatus(MissionRequestDTO.FindIndividualMissionDto missionDto){
        if(missionDto.getMissionStatus().equals(MissionStatus.CHALLENGING.name())) {
            System.out.println("Challenging Mission 조회");
            return missionRepository.findIndividualChallengingMission(missionDto.getMemberId());
        }
        else if(missionDto.getMissionStatus().equals(MissionStatus.COMPLETE.name())){
            System.out.println("Complete Mission 조회");
            return missionRepository.findIndividualCompleteMission(missionDto.getMemberId());
        }
        else{
            System.out.println("Claimable Mission 조회");
            return missionRepository.findIndividualClaimableMission(missionDto);
        }
    }
}
