package org.example.javastringboots.mapper;

import org.example.javastringboots.dto.req.ProductRequestDTO;
import org.example.javastringboots.dto.res.ProductResDTO;
import org.example.javastringboots.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDTO product);
    ProductResDTO toDTO(Product product);
}
