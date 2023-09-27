package com.sylph.bobmukja.api.web.dto.external;

import com.sylph.bobmukja.api.domain.entity.Review;
import com.sylph.bobmukja.api.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewSearchRequest {

    @Schema(description = "점수", defaultValue = "0")
    private Integer score;

    @Schema(description = "리뷰 제목", defaultValue = "리뷰")
    private String title;

    @Schema(description = "장소 내용", defaultValue = "내용")
    private String content;

    @Builder
    public ReviewSearchRequest(Integer score, String title, String content) {
        this.score = score;
        this.title = title;
        this.content = content;
    }

    public ReviewSearchRequest() {
        this.score = 0;
        this.title = "";
        this.content = "";
    }
}
