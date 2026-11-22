package com.api_test.book.mapper;

import com.api_test.book.dto.CategoryDTO;
import com.api_test.book.dto.CategoryFormDTO;
import com.api_test.book.dto.CategoryGridDTO;
import com.api_test.book.model.Category;

public final class CategoryMapper {

    private CategoryMapper() {}

    public static Category toEntity(CategoryFormDTO form) {
        if (form == null) return null;
        Category entity = new Category();
        entity.setId(form.getId());
        entity.setName(form.getName());
        entity.setDescription(form.getDescription());
        entity.setIsActive(form.getIsActive() != null ? form.getIsActive() : true);
        return entity;
    }

    public static CategoryFormDTO toForm(Category entity) {
        if (entity == null) return null;
        CategoryFormDTO form = new CategoryFormDTO();
        form.setId(entity.getId());
        form.setName(entity.getName());
        form.setDescription(entity.getDescription());
        form.setIsActive(entity.getIsActive());
        return form;
    }

    public static CategoryGridDTO toGrid(Category entity) {
        if (entity == null) return null;
        CategoryGridDTO grid = new CategoryGridDTO();
        grid.setId(entity.getId());
        grid.setName(entity.getName());
        grid.setDescription(entity.getDescription());
        grid.setIsActive(entity.getIsActive());
        return grid;
    }

    public static CategoryDTO toDTO(Category entity) {
        if (entity == null) return null;
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }
}