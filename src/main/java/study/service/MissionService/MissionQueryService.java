package study.service.MissionService;

import study.web.dto.MissionRequestDTO;
import study.web.dto.MissionResponseDTO;

import java.util.List;
public interface MissionQueryService {
    List<MissionResponseDTO.FindIndividualMissionResultDto> findIndividualMissionByMissionStatus(MissionRequestDTO.FindIndividualMissionDto missionDto);
}
