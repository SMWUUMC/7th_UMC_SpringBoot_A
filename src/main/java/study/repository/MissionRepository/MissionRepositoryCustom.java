package study.repository.MissionRepository;

import study.domain.Mission;
import study.domain.enums.MissionStatus;
import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

import java.util.List;
public interface MissionRepositoryCustom {
    List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualChallengingMission(Long memberId);
    List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualCompleteMission(Long memberId);
    List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualClaimableMission(MissionRequestDTO.FindIndividualMissionDto request);
}
