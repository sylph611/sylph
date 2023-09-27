package com.sylph.bobmukja.api.web.dto.internal;

import com.sylph.bobmukja.api.domain.entity.PlaceCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category {
    @Schema(description = "장소 카테고리 타입(MAIN/SUB)")
    private String type;

    @Schema(description = "상위 코드")
    private String upperCode;

    @Schema(description = "코드")
    private String code;

    @Schema(description = "값")
    private String value;

    @Builder
    public Category(String type, String upperCode, String code, String value) {
        this.type = type;
        this.upperCode = upperCode;
        this.code = code;
        this.value = value;
    }

    public static Category of(PlaceCategory placeCategory) {
        if(ObjectUtils.isEmpty(placeCategory)) return Category.builder().build();
        return Category.builder()
                .type(placeCategory.getType())
                .upperCode(placeCategory.getUpperCode())
                .code(placeCategory.getCode())
                .value(placeCategory.getValue())
                .build();
    }

    public static List<Category> ofList(List<PlaceCategory> placeCategoryList) {
        if(ObjectUtils.isEmpty(placeCategoryList)) return new ArrayList<>();
        return placeCategoryList.stream()
                .map(Category::of)
                .toList();
    }
}
