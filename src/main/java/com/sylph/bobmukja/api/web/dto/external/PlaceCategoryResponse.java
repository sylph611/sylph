package com.sylph.bobmukja.api.web.dto.external;

import com.sylph.bobmukja.api.web.dto.internal.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PlaceCategoryResponse {

    @Schema(description = "장소 카테고리")
    private List<Category> categories;

    @Builder
    public PlaceCategoryResponse(List<Category> categories) {
        this.categories = categories;
    }

    public static PlaceCategoryResponse of(List<Category> categories) {
        return PlaceCategoryResponse.builder()
                .categories(categories)
                .build();
    }
}
