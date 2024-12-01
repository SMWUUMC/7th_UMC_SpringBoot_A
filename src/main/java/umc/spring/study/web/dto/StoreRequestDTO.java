package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class StoreRequestDTO {

    @Getter
    @Setter
    public static class CreateStoreDto {

        @NotBlank
        private String name;

        @NotBlank
        private String address;

        @NotNull
        private Long regionId;

    }

}