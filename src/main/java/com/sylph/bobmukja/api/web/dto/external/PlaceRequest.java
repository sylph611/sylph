package com.sylph.bobmukja.api.web.dto.external;

import com.sylph.bobmukja.api.domain.entity.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class PlaceRequest {

    @Schema(description = "PLACE ID")
    private Long id;

    @Schema(description = "장소명")
    private String name;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "장소 대분류")
    private String placeCategory;

    @Schema(description = "장소 소분류")
    private String placeSubCategory;

    @Schema(description = "지역 대분류")
    private String region;

    @Schema(description = "지역 소분류")
    private String subRegion;

    @Schema(description = "영업시간")
    private String businessHours;

    @Schema(description = "전화번호")
    private String phoneNumber;

    @Schema(description = "위도")
    private Double latitude;

    @Schema(description = "경도")
    private Double longitude;

    @Schema(description = "삭제여부")
    private Boolean deleted;

    @Builder
    private PlaceRequest(Long id, String name, String address, String placeCategory, String placeSubCategory, String region, String subRegion, String businessHours, String phoneNumber, Double latitude, Double longitude, boolean deleted) {
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
        this.deleted = deleted;
    }

    public Place toEntity() {
        return Place.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .placeCategory(this.placeCategory)
                .placeSubCategory(this.placeSubCategory)
                .region(this.region)
                .subRegion(this.subRegion)
                .businessHours(this.businessHours)
                .phoneNumber(this.phoneNumber)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .deleted(this.deleted)
                .build();
    }

}
