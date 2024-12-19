package org.example.javastringboots.service;

import org.example.javastringboots.dto.req.CategoryRequestDTO;
import org.example.javastringboots.dto.res.CategoryResDTO;
import org.example.javastringboots.entity.Category;
import org.example.javastringboots.mapper.CategoryMapper;
import org.example.javastringboots.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResDTO> all(){
        return categoryRepository.findAll().stream().map(categoryMapper::toDTO).toList();
    }


    public CategoryResDTO create(CategoryRequestDTO category){
        //Category c = categoryMapper.toEntity(category);
        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toEntity(category)));
    }
}
