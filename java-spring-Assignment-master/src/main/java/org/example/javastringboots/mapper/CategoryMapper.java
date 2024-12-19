package org.example.javastringboots.mapper;

import org.example.javastringboots.dto.req.CategoryRequestDTO;
import org.example.javastringboots.dto.res.CategoryResDTO;
import org.example.javastringboots.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDTO categoryReq);
    CategoryResDTO toDTO(Category category);
}
