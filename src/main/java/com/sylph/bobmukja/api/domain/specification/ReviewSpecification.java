package com.sylph.bobmukja.api.domain.specification;

import com.sylph.bobmukja.api.domain.entity.Review;
import com.sylph.bobmukja.api.web.dto.external.ReviewSearchRequest;
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

    /**
     * NotDeleted
     * @return
     */
    public static Specification<Review> notDeleted() {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.isFalse(root.get("deleted"));
    }

    /**
     * 장소 ID 조회
     * @param placeId
     * @return
     */
    public static Specification<Review> eqaulPlaceId(Long placeId) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("placeId"),placeId);
    }

    public static Specification<Review> search(Long placeId, ReviewSearchRequest reviewSearchRequest) {
        Specification<Review> spec = (root, query, criteriaBuilder) -> null;
        if(ObjectUtils.isEmpty(reviewSearchRequest)) reviewSearchRequest = new ReviewSearchRequest();

        spec = spec.and(ReviewSpecification.notDeleted());
        spec = spec.and(ReviewSpecification.eqaulPlaceId(placeId));
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
