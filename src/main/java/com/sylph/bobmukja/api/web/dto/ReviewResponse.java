package com.sylph.bobmukja.api.web.dto;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.domain.entity.Review;
import com.sylph.bobmukja.api.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReviewResponse {

    @Schema(description = "REVIEW ID")
    private Long id;

    @Schema(description = "PLACE ID(장소)")
    private Long placeId;

    @Schema(description = "점수")
    private Integer score;

    @Schema(description = "방문 일자")
    private LocalDate visitDate;

    @Schema(description = "리뷰 제목")
    private String title;

    @Schema(description = "장소 내용")
    private String content;

    @Schema(description = "작성자 ID")
    private String writerId;

    @Schema(description = "작성자 이름")
    private String writerName;

    @Schema(description = "작성자 닉네임")
    private String writerNickName;

    @Builder
    private ReviewResponse(Long id, Long placeId, Integer score, LocalDate visitDate, String title, String content, String writerId, String writerName, String writerNickName) {
        this.id = id;
        this.placeId = placeId;
        this.score = score;
        this.visitDate = visitDate;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.writerName = writerName;
        this.writerNickName = writerNickName;
    }

    public static ReviewResponse of(Review review) {
        if(ObjectUtils.isEmpty(review)) return null;
        return ReviewResponse.builder()
                .id(review.getId())
                .placeId(review.getPlaceId())
                .score(review.getScore())
                .visitDate(review.getVisitDate())
                .title(review.getTitle())
                .content(review.getContent())
                //.writerId(user.getId().toString())
                //.writerName(user.getName())
                //.writerNickName(user.getNickname())
                .build();
    }

    public static List<ReviewResponse> ofList(List<Review> reviewList) {
        return reviewList.stream()
                .map(ReviewResponse::of)
                .toList();
    }
}
