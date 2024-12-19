package org.example.javastringboots.controller;

import org.example.javastringboots.dto.req.ProductRequestDTO;
import org.example.javastringboots.dto.res.ProductResDTO;
import org.example.javastringboots.entity.Product;
import org.example.javastringboots.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<ProductResDTO> getAllProduct(){
        return productService.all();
    }

    @PostMapping("create")
    public ResponseEntity<ProductResDTO> createProduct(@RequestBody ProductRequestDTO product){
        return ResponseEntity.ok(productService.create(product));
    }
    @PostMapping("search")
    public List<ProductResDTO> search(@RequestBody ProductRequestDTO product
                                      ){
        System.out.println("Searching for: name=" + product.getName() + ", price=" + product.getPrice());

        return productService.findAllByNameAndPrice(product.getName(), product.getPrice());
    }
}
