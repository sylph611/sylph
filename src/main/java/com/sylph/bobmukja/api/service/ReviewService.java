package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.domain.entity.Review;
import com.sylph.bobmukja.api.domain.repository.ReviewRepository;
import com.sylph.bobmukja.api.web.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review get(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getList(Long id) {
        return reviewRepository.findByPlaceIdAndDeletedIsFalse(id);
    }

    public boolean save(List<ReviewRequest> reviewRequestList) {
        reviewRequestList.stream()
                .map(reviewRequest -> reviewRepository.save(reviewRequest.toEntity()));
        return true;
    }

    public boolean delete(List<Long> reviewIdList) {
        List<Review> reviewList = reviewIdList.stream().map(r -> reviewRepository.findByIdAndDeletedIsFalse(r).orElse(null)).toList();
        reviewList.stream().map(Review::deleted);
        return true;
    }

}
