package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.service.ReviewService;
import com.sylph.bobmukja.api.web.dto.ReviewRequest;
import com.sylph.bobmukja.api.web.dto.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @Operation(description  = "리뷰 리스트 조회")
    @GetMapping("")
    public ResponseEntity<List<ReviewResponse>> getList(@Schema(description = "Place ID", example = "1")
                                                            @PathVariable Long id) {
        return ResponseEntity.of(Optional.of(ReviewResponse.ofList(reviewService.getList(id))));
    }

    @Operation(description  = "리뷰 단건 조회")
    @GetMapping("{id}")
    public ResponseEntity<ReviewResponse> get(@Schema(description = "Place ID", example = "1")
                                             @PathVariable Long id) {
        return ResponseEntity.of(Optional.of(ReviewResponse.of(reviewService.get(id))));
    }

    @Operation(description  = "장소 저장(다건)")
    @PostMapping("")
    public ResponseEntity<Boolean> save(@Schema(description = "장소 정보") List<ReviewRequest> reviewRequestList) {
        return ResponseEntity.of(
                Optional.of(
                        reviewService.save(reviewRequestList)
                )
        );
    }

    @Operation(description  = "장소 삭제(다건)")
    @DeleteMapping("")
    public ResponseEntity<Boolean> delete(@Schema(description = "장소 정보") List<Long> reviewList) {
        return ResponseEntity.of(
                Optional.of(
                        reviewService.delete(reviewList)
                )
        );
    }
}
