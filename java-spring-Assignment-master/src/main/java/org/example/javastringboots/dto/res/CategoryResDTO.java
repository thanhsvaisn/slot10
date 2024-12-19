package org.example.javastringboots.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class CategoryResDTO {
    private Long id;
    private String name;

    public CategoryResDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
