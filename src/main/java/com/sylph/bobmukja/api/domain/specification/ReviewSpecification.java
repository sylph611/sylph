package com.sylph.bobmukja.api.domain.specification;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.domain.entity.Review;
import com.sylph.bobmukja.api.web.dto.ReviewSearchRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {

    /**
     * 리뷰 제목 조회 (LIKE)
     * @param title
     * @return
     */
    public static Specification<Review> likeTitle(String title) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("title"),"%"+title+"%");
    }

    /**
     * 리뷰 내용 조회 (LIKE)
     * @param content
     * @return
     */
    public static Specification<Review> likeContent(String content) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("content"),"%"+content+"%");
    }

    /**
     * 점수 비교
     * @param score
     * @return
     */
    public static Specification<Review> moreThanScore(int score) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.greaterThanOrEqualTo(root.get("score"), score);
    }

    public static Specification<Review> notDeleted() {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.isFalse(root.get("deleted"));
    }

    public static Specification<Review> search(ReviewSearchRequest reviewSearchRequest) {
        Specification<Review> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(ReviewSpecification.notDeleted());

        // 제목
        if(ObjectUtils.isNotEmpty(reviewSearchRequest.getTitle())) {
            spec = spec.and(ReviewSpecification.likeTitle(reviewSearchRequest.getTitle()));
        }
        // 내용
        if(ObjectUtils.isNotEmpty(reviewSearchRequest.getContent())) {
            spec = spec.and(ReviewSpecification.likeContent(reviewSearchRequest.getContent()));
        }
        // 점수
        if(ObjectUtils.isNotEmpty(reviewSearchRequest.getScore())) {
            spec = spec.and(ReviewSpecification.moreThanScore(reviewSearchRequest.getScore()));
        }

        return spec;
    }
}
