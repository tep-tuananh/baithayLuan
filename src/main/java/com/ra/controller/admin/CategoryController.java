package com.ra.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/categories")
public class CategoryController {
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>("Danh sách", HttpStatus.OK);
    }
}
