package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.constant.PlaceType;
import com.sylph.bobmukja.api.service.PlaceCategoryService;
import com.sylph.bobmukja.api.web.dto.external.PlaceCategoryAllResponse;
import com.sylph.bobmukja.api.web.dto.external.PlaceCategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/placeCategory")
@RequiredArgsConstructor
@Tag(name = "4. 장소 카테고리 API", description = "4. 장소 카테고리 API")
public class PlaceCategoryController {

    private final PlaceCategoryService placeCategoryService;

    @Operation(description  = "장소 카테고리(MAIN/SUB) 모두")
    @GetMapping("/")
    public ResponseEntity<PlaceCategoryAllResponse> getAllPlaceCategories() {
        return ResponseEntity.of(Optional.of(placeCategoryService.getAllPlaceCategories()));
    }

    @Operation(description  = "장소 카테고리 type별 조회")
    @GetMapping("/{type}")
    public ResponseEntity<PlaceCategoryResponse> getPlaceCategories(@PathVariable(required = true) String type,
                                                                    @RequestParam(name = "상위코드", required = false, defaultValue = "") String upperCode) {
        return ResponseEntity.of(Optional.of(placeCategoryService.getPlaceCategories(PlaceType.valueOf(StringUtils.upperCase(type)), upperCode)));
    }

}
