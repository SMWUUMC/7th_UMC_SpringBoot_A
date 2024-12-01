package umc.spring.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Store;
import umc.spring.study.service.StoreService.StoreCommandService;
import umc.spring.study.web.dto.StoreRequestDTO;
import umc.spring.study.web.dto.StoreResponseDTO;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    // 가게 생성 API
    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.StoreResultDTO> createStore(
            @RequestBody @Valid StoreRequestDTO.CreateStoreDto request) {
        // 요청 DTO -> 엔티티 저장
        Store store = storeCommandService.createStore(request);

        // 저장된 Store -> 응답 DTO 변환
        return ApiResponse.onSuccess(StoreConverter.toStoreResultDTO(store));
    }
}
