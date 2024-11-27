package study.web.dto;

import lombok.*;

import java.time.LocalDateTime;

public class StoreResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddStoreResultDTO{
        Long storeId;
        String storeName;
        String regionName;
        LocalDateTime createdAt;
    }
}
