package umc_7th.spring.service.RegionService;

import umc_7th.spring.domain.Store;
import umc_7th.spring.web.dto.RegionDTO.RegionRequestDTO;

public interface RegionCommandService {

    // 1. 특정 지역에 가게 추가하기 API
    Store createStore(Long regionId, RegionRequestDTO.StoreDTO request);
}
