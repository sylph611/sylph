package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.service.PlaceService;
import com.sylph.bobmukja.api.web.dto.PlaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
@Tag(name = "장소 API", description = "장소 API")
public class PlaceController {

    private final PlaceService placeService;

    @Operation(description  = "장소 단건 조회")
    @GetMapping("{id}")
    public ResponseEntity<PlaceResponse> get(@Schema(description = "Place ID", example = "1")
                    @PathVariable  Long id) {
        return ResponseEntity.of(Optional.of(PlaceResponse.of(placeService.get(id))));
    }

    @Operation(description  = "장소 단건 조회")
    @GetMapping("")
    public ResponseEntity<List<PlaceResponse>> get() {
        return ResponseEntity.of(Optional.of(PlaceResponse.ofList(placeService.getList())));
    }

}
