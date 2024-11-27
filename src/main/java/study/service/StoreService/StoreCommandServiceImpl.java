package study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain.Store;
import study.repository.RegionRepository.RegionRepository;
import study.repository.StoreRepository.StoreRepository;
import study.web.dto.StoreRequestDTO;
import study.converter.StoreConverter;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    @Override
    public Store addStore(StoreRequestDTO.AddStoreDTO request) {
        Store store = StoreConverter.toStore(request);
        store.setRegion(regionRepository.findById(request.getRegionId()).get());
        return storeRepository.save(store);
    }
}
