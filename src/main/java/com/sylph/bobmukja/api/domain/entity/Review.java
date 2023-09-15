package com.sylph.bobmukja.api.domain.entity;

import com.sylph.bobmukja.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("장소")
    private Long placeId;

    @Column(nullable = false)
    @Comment("점수")
    private Integer score;

    @Column()
    @Comment("방문 일자")
    private LocalDate visitDate;

    @Column(nullable = false, length = 300)
    @Comment("리뷰 제목")
    private String title;

    @Column(nullable = false, length = 2000)
    @Comment("리뷰 내용")
    private String content;

    @Column()
    @Comment("삭제여부")
    private boolean deleted;

    @Builder
    public Review(Long id, Long placeId, Integer score, LocalDate visitDate, String title, String content, boolean deleted) {
        this.id = id;
        this.placeId = placeId;
        this.score = score;
        this.visitDate = visitDate;
        this.title = title;
        this.content = content;
        this.deleted = deleted;
    }

    public Review deleted() {
        this.deleted = true;
        return this;
    }
}
