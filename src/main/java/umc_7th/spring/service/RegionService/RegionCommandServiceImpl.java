package umc_7th.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc_7th.spring.converter.RegionConverter;
import umc_7th.spring.domain.Store;
import umc_7th.spring.repository.RegionRepository;
import umc_7th.spring.repository.StoreRepository.StoreRepository;
import umc_7th.spring.web.dto.RegionDTO.RegionRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService {

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Store createStore (Long regionId, RegionRequestDTO.StoreDTO request) { // 1. 특정 지역에 가게 추가하기 API
        Store store = RegionConverter.toStore(request);
        store.setRegion(regionRepository.findById(regionId).get());

        return storeRepository.save(store);
    }
}
