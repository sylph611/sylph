package com.sylph.bobmukja.api.web.dto.external;

import com.sylph.bobmukja.api.web.dto.internal.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PlaceCategoryAllResponse {

    @Schema(description = "MAIN 장소 카테고리 (대분류)")
    private List<Category> mainCategories;

    @Schema(description = "SUB 장소 카테고리 (대분류)")
    private List<Category> subCategories;

    @Builder
    public PlaceCategoryAllResponse(List<Category> mainCategories, List<Category> subCategories) {
        this.mainCategories = mainCategories;
        this.subCategories = subCategories;
    }

    public static PlaceCategoryAllResponse of(List<Category> mainCategories, List<Category> subCategories) {
        return PlaceCategoryAllResponse.builder()
                .mainCategories(mainCategories)
                .subCategories(subCategories)
                .build();
    }
}
