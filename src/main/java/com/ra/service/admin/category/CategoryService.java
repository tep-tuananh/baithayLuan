package com.ra.service.admin.category;

import com.ra.model.dto.response.CategoryReponse;
import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryService {
    Page<CategoryReponse> getAll(Pageable pageable);
    Category findById(Long id);
    Category saveOrUpdate(Category category);
    void delete(Long id);
}
