package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.service.PlaceService;
import com.sylph.bobmukja.api.web.dto.external.MapRequest;
import com.sylph.bobmukja.api.web.dto.external.PlaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
@Tag(name = "3. 지도 검색 API", description = "3. 지도 검색 API")
public class MapController {

    private final PlaceService placeService;

    @Operation(description  = "현재 지도내 조회[사각형범위]")
    @GetMapping("/search/inner")
    public ResponseEntity<Page<PlaceResponse>> searchInCurrentMap(@Valid MapRequest mapRequest, int page) {
        return ResponseEntity.of(
                Optional.of(
                        placeService.searchInCurrentMap(mapRequest, page)
                )
        );
    }

    @Operation(description  = "현재 위치기준 조회[중심좌표기준 반경거리]")
    @GetMapping("/search")
    public ResponseEntity<Page<PlaceResponse>> search(@Valid MapRequest mapRequest, int page) {
        return ResponseEntity.of(
                Optional.of(
                        placeService.search(mapRequest, page)
                )
        );
    }
}
