package umc.spring.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class StoreResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor

    public static class StoreResultDTO {
        private Long id;
        private String name;
        private String address;
        private String regionName;
        private Float score;
    }
}
