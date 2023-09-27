package com.sylph.bobmukja.api.web.dto.internal;

import com.sylph.bobmukja.api.domain.entity.PlaceRegion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Region {
    @Schema(description = "지역 타입(MAIN/SUB)")
    private String type;

    @Schema(description = "상위 코드")
    private String upperCode;

    @Schema(description = "코드")
    private String code;

    @Schema(description = "값")
    private String value;

    @Builder
    public Region(String type, String upperCode, String code, String value) {
        this.type = type;
        this.upperCode = upperCode;
        this.code = code;
        this.value = value;
    }

    public static Region of(PlaceRegion placeRegion) {
        if(ObjectUtils.isEmpty(placeRegion)) return Region.builder().build();
        return Region.builder()
                .type(placeRegion.getType())
                .upperCode(placeRegion.getUpperCode())
                .code(placeRegion.getCode())
                .value(placeRegion.getValue())
                .build();
    }

    public static List<Region> ofList(List<PlaceRegion> placeRegionList) {
        if(ObjectUtils.isEmpty(placeRegionList)) return new ArrayList<>();
        return placeRegionList.stream()
                .map(Region::of)
                .toList();
    }
}
