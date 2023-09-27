package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.service.ReviewService;
import com.sylph.bobmukja.api.web.dto.external.ReviewRequest;
import com.sylph.bobmukja.api.web.dto.external.ReviewResponse;
import com.sylph.bobmukja.api.web.dto.external.ReviewSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places/{placeId}/reviews")
@RequiredArgsConstructor
@Tag(name = "2. 리뷰 API", description = "2. 리뷰 API")
public class ReviewController {

    private final ReviewService reviewService;
    @Operation(description  = "리뷰 리스트 조회")
    @GetMapping("")
    public ResponseEntity<Page<ReviewResponse>> search(@Schema(description = "Place ID", example = "1") @PathVariable Long placeId,
                                                       @RequestParam(required = false) ReviewSearchRequest reviewSearchRequest,
                                                       @RequestParam(defaultValue = "0", required = false) int page) {
        return ResponseEntity.of(Optional.of(ReviewResponse.of(reviewService.search(placeId, reviewSearchRequest, page))));
    }

    @Operation(description  = "리뷰 단건 조회")
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> get(@Schema(description = "REVIEW ID", example = "1")
                                             @PathVariable Long reviewId) {
        return ResponseEntity.of(Optional.of(ReviewResponse.of(reviewService.get(reviewId))));
    }

    @Operation(description  = "리뷰 저장(다건)")
    @PostMapping("")
    public ResponseEntity<Boolean> save(@Schema(description = "장소 정보") List<ReviewRequest> reviewRequestList) {
        return ResponseEntity.of(
                Optional.of(
                        reviewService.save(reviewRequestList)
                )
        );
    }

    @Operation(description  = "리뷰 삭제(다건)")
    @DeleteMapping("")
    public ResponseEntity<Boolean> delete(@Schema(description = "장소 정보") List<Long> reviewList) {
        return ResponseEntity.of(
                Optional.of(
                        reviewService.delete(reviewList)
                )
        );
    }
}
