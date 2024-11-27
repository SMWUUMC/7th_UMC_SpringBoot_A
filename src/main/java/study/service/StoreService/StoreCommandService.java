package study.service.StoreService;

import study.domain.Store;
import study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.AddStoreDTO request);
}
