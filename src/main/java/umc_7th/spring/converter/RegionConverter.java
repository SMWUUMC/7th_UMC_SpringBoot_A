package umc_7th.spring.converter;

import umc_7th.spring.domain.Store;
import umc_7th.spring.web.dto.RegionDTO.RegionRequestDTO;
import umc_7th.spring.web.dto.RegionDTO.RegionResponseDTO;

import java.time.LocalDateTime;

public class RegionConverter {

    public static Store toStore (RegionRequestDTO.StoreDTO request) { // 1. 특정 지역에 가게 추가하기 API
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
    public static RegionResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store) {
        return RegionResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
