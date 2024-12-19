package org.example.javastringboots.controller;

import org.example.javastringboots.dto.req.CategoryRequestDTO;
import org.example.javastringboots.dto.res.CategoryResDTO;
import org.example.javastringboots.entity.Category;
import org.example.javastringboots.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryControlller {
    private CategoryService categoryService;

    public CategoryControlller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryResDTO> getAllCategory(){
        return categoryService.all();
    }
    @PostMapping()
    public ResponseEntity<CategoryResDTO> createCategory(@RequestBody CategoryRequestDTO category){
        return ResponseEntity.ok(categoryService.create(category));
    }
}
