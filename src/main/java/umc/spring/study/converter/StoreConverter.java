package umc.spring.study.converter;

import umc.spring.study.domain.Region;
import umc.spring.study.domain.Store;
import umc.spring.study.web.dto.StoreRequestDTO;
import umc.spring.study.web.dto.StoreResponseDTO;

public class StoreConverter {

    // StoreRequestDTO.CreateReviewDto -> Store 엔티티 변환
    public static Store toStore(StoreRequestDTO.CreateStoreDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }

    // Store 엔티티 -> StoreResponseDTO.StoreResultDTO 변환
    public static StoreResponseDTO.StoreResultDTO toStoreResultDTO(Store store) {
        return StoreResponseDTO.StoreResultDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .regionName(store.getRegion().getName())
                .score(store.getScore())
                .build();
    }
}

