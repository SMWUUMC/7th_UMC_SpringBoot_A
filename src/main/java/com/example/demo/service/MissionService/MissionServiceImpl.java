package com.example.demo.service.MissionService;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Store;
import com.example.demo.domain.mapping.MemberMission;
import com.example.demo.dto.MissionRequestDTO;
import com.example.demo.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;

    @Override
    public Mission assignMissionToStore(MissionRequestDTO.AssignMissionToStoreDto request) {
        Mission mission = new Mission();
        Store store = new Store(); // Replace with the actual logic to retrieve a store
        store.setId(request.getStoreId()); // Assuming `getStoreId()` exists in `request`

        mission.setStore(store); // Using the `setStore` method
        return missionRepository.save(mission);
    }

    @Override
    public MemberMission changeMissionStatus(MissionRequestDTO.changeMissionStatusDto request) {
        // Implementation logic here
        return new MemberMission(); // Replace with actual implementation
    }
}
