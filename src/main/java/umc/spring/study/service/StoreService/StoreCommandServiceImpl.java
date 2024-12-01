package umc.spring.study.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Region;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.RegionRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateStoreDto request) {
        // 유효한 지역 확인
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found"));

        // DTO -> 엔티티 변환
        Store store = StoreConverter.toStore(request, region);

        // Store 저장
        return storeRepository.save(store);
    }
}
