package umc_7th.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc_7th.spring.apiPayload.ApiResponse;
import umc_7th.spring.converter.RegionConverter;
import umc_7th.spring.domain.Store;
import umc_7th.spring.service.RegionService.RegionCommandService;
import umc_7th.spring.validation.annotation.ExistRegion;
import umc_7th.spring.web.dto.RegionDTO.RegionRequestDTO;
import umc_7th.spring.web.dto.RegionDTO.RegionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionRestController {

    private final RegionCommandService regionCommandService;

    @PostMapping("/{regionId}/store") // 1. 특정 지역에 가게 추가하기 API
    public ApiResponse<RegionResponseDTO.CreateStoreResultDTO> createStore (@RequestBody @Valid RegionRequestDTO.StoreDTO request,
                                                                            @ExistRegion @PathVariable(name = "regionId") Long regionId) {
        Store store = regionCommandService.createStore(regionId, request);

        return ApiResponse.onSuccess(RegionConverter.toCreateStoreResultDTO(store));
    }
}
