package study.converter;

import study.domain.Store;
import study.web.dto.StoreRequestDTO;
import study.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {
    public static Store toStore(StoreRequestDTO.AddStoreDTO dto){
        return Store.builder()
                .region(null)
                .address(dto.getStoreAddress())
                .score(0f)
                .name(dto.getStoreName())
                .build();
    }

    public static StoreResponseDTO.AddStoreResultDTO toAddStoreResultDTO(Store store){
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .storeName(store.getName())
                .storeId(store.getId())
                .regionName(store.getRegion().getName())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
