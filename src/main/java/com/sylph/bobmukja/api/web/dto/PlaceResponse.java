package com.sylph.bobmukja.api.web.dto;

import com.sylph.bobmukja.api.domain.entity.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlaceResponse {

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
    private PlaceResponse(Long id, String name, String address, String placeCategory, String placeSubCategory, String region, String subRegion, String businessHours, String phoneNumber, Double latitude, Double longitude, Boolean deleted) {
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

    public static PlaceResponse of(Place place) {
        if(ObjectUtils.isEmpty(place)) return PlaceResponse.builder().build();
        return PlaceResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .placeCategory(place.getPlaceCategory())
                .placeSubCategory(place.getPlaceSubCategory())
                .region(place.getRegion())
                .subRegion(place.getSubRegion())
                .businessHours(place.getBusinessHours())
                .phoneNumber(place.getPhoneNumber())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .deleted(place.isDeleted())
                .build();
    }

    public static List<PlaceResponse> ofList(List<Place> placeList) {
        if(ObjectUtils.isEmpty(placeList)) return new ArrayList<>();
        return placeList.stream()
                .map(PlaceResponse::of)
                .toList();
    }

    public static Page<PlaceResponse> of(Page<Place> placeList) {
        if(ObjectUtils.isEmpty(placeList)) return new PageImpl<>(PlaceResponse.ofList(null), Pageable.ofSize(1), 0);
        return placeList.map(PlaceResponse::of);
    }
}
