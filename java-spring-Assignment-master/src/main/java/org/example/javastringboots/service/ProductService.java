package org.example.javastringboots.service;

import org.example.javastringboots.dto.req.ProductRequestDTO;
import org.example.javastringboots.dto.res.ProductResDTO;
import org.example.javastringboots.entity.Category;
import org.example.javastringboots.entity.Product;
import org.example.javastringboots.mapper.ProductMapper;
import org.example.javastringboots.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResDTO> all(){
        return productRepository.findAll().stream().map(productMapper::toDTO).toList();
    }


    public ProductResDTO create(ProductRequestDTO product){

        return productMapper.toDTO(productRepository.save(productMapper.toEntity(product)));
    }


    public List<ProductResDTO> findByNameOrPrice(String name, Double price) {


        List<Product> products = productRepository.findAllByNameOrPrice(name, price);

        System.out.println(products);

        return products.stream()
                .map(product -> new ProductResDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty(),
                        product.getDescription(),
                        (List<Category>) product.getCategory()
                ))
                .collect(Collectors.toList());

    }
    public List<ProductResDTO> findAllByNameAndPrice(String name, Double price) {


        List<Product> products = productRepository.findAllByNameOrPrice(name, price);

        return products.stream()
                .map(product -> new ProductResDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty(),
                        product.getDescription(),
                        (List<Category>) product.getCategory()
                ))
                .collect(Collectors.toList());
    }


}
