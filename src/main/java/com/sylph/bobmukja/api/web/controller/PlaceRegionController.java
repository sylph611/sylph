package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.constant.PlaceType;
import com.sylph.bobmukja.api.service.PlaceCategoryService;
import com.sylph.bobmukja.api.service.PlaceRegionService;
import com.sylph.bobmukja.api.web.dto.external.PlaceCategoryAllResponse;
import com.sylph.bobmukja.api.web.dto.external.PlaceCategoryResponse;
import com.sylph.bobmukja.api.web.dto.external.PlaceRegionAllResponse;
import com.sylph.bobmukja.api.web.dto.external.PlaceRegionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/placeRegion")
@RequiredArgsConstructor
@Tag(name = "5. 장소 지역 API", description = "5. 장소 지역 API")
public class PlaceRegionController {

    private final PlaceRegionService placeRegionService;

    @Operation(description  = "장소 지역(MAIN/SUB) 모두")
    @GetMapping("/")
    public ResponseEntity<PlaceRegionAllResponse> getAllPlaceRegions() {
        return ResponseEntity.of(Optional.of(placeRegionService.getAllPlaceRegions()));
    }

    @Operation(description  = "장소 지역 type별 조회")
    @GetMapping("/{type}")
    public ResponseEntity<PlaceRegionResponse> getPlaceRegions(@PathVariable(required = true) String type,
                                                               @RequestParam(name = "상위코드", required = false, defaultValue = "") String upperCode) {
        return ResponseEntity.of(Optional.of(placeRegionService.getPlaceRegions(PlaceType.valueOf(StringUtils.upperCase(type)), upperCode)));
    }

}
