package com.sylph.bobmukja.api.domain.entity;

import com.sylph.bobmukja.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "place")
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @Comment("식당명")
    private String name;

    @Column(length = 200)
    @Comment("주소")
    private String address;

    @Column(length = 100)
    @Comment("장소 대분류")
    private String placeCategory;

    @Column(length = 100)
    @Comment("장소 소분류")
    private String placeSubCategory;

    @Column(length = 100)
    @Comment("지역 대분류")
    private String region;

    @Column(length = 100)
    @Comment("지역 소분류")
    private String subRegion;

    @Column(length = 100)
    @Comment("영업시간")
    private String businessHours;

    @Column(length = 100)
    @Comment("전화번호")
    private String phoneNumber;

    @Column(length = 100)
    @Comment("위도")
    private String latitude;

    @Column(length = 100)
    @Comment("경도")
    private String longitude;

    @Builder
    private Place(Long id, String name, String address, String placeCategory, String placeSubCategory, String region, String subRegion, String businessHours, String phoneNumber, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.placeCategory = placeCategory;
        this.placeSubCategory = placeSubCategory;
        this.region = region;
        this.subRegion = subRegion;
        this.businessHours = businessHours;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
