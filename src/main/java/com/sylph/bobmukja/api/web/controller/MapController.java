package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.service.PlaceService;
import com.sylph.bobmukja.api.web.dto.MapRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
@Tag(name = "지도 API", description = "지도 API")
public class MapController {

    private final PlaceService placeService;

    @Operation(description  = "맵 조회")
    @GetMapping("/search")
    public ResponseEntity<String> get(@RequestBody @Valid MapRequest mapRequest) {
        return ResponseEntity.of(
                Optional.of(
                        placeService.search(mapRequest)
                )
        );
    }
}
