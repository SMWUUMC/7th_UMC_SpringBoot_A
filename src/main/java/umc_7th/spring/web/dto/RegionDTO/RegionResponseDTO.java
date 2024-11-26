package umc_7th.spring.web.dto.RegionDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class RegionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreResultDTO { // 1. 특정 지역에 가게 추가하기 API
        private Long storeId;
        private LocalDateTime createdAt;
    }
}
