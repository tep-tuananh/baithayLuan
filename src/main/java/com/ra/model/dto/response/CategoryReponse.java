package com.ra.model.dto.response;

import com.ra.model.entity.Category;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryReponse {
    private Long id;
    private String categoryName;
    private Boolean status;

    public  CategoryReponse(Category category)
    {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.status = category.getStatus();
    }
}
