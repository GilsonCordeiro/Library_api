package com.api_test.book.dto;

import com.api_test.book.model.Category;

import java.io.Serializable;

public class CategoryDto implements Serializable {
    private static final long serialVersionUID= 1L;
    private Long id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Category obj) {
        this.id = obj.getId();
        this.name = obj.getName();
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
