package org.example.javastringboots.dto.res;

import org.example.javastringboots.entity.Category;

import java.util.List;

public class ProductResDTO {
    private Long id;
    private String name;
    private Double price;
    private Long qty;
    private String description;

    private List<Category> categories;

    public ProductResDTO(Long id, String name, Double price, Long qty, String description, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.description = description;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
