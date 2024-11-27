package study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.validation.annotation.ExistRegion;

public class StoreRequestDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddStoreDTO {
        String storeName;
        String storeAddress;

        @ExistRegion
        Long regionId;
    }
}
