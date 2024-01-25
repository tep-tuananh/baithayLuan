package com.ra.controller.admin;

import com.ra.model.dto.response.CategoryReponse;
import com.ra.model.entity.Category;
import com.ra.service.admin.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    // lấy ra danh sách category
    @GetMapping("/categories")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "5",name = "limit") int limit,
    @RequestParam(defaultValue = "0",name = "page") int page,
    @RequestParam(defaultValue = "categoryName",name = "sort") String sort,
     @RequestParam(defaultValue = "asc", name = "oder") String oder
     )
    {
        Pageable pageable;
        if(oder.equals("asc"))
        {
            pageable = PageRequest.of(page,limit, Sort.by(sort).ascending());
        }else
        {
           pageable = PageRequest.of(page,limit, Sort.by(sort).descending());
        }

        Page<CategoryReponse> categoryList = categoryService.getAll(pageable);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    @PostMapping("/categories-add")
    public  ResponseEntity<?> create(@RequestBody Category category)
    {
        Category categoryNew= categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(categoryNew,HttpStatus.OK);
    }
}
