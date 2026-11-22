package com.api_test.book.service;

import com.api_test.book.dto.CategoryDTO;
import com.api_test.book.dto.CategoryFilterDTO;
import com.api_test.book.dto.CategoryFormDTO;
import com.api_test.book.dto.CategoryGridDTO;
import com.api_test.book.mapper.CategoryMapper;
import com.api_test.book.model.Category;
import com.api_test.book.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return repository.findAll().stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CategoryGridDTO> findByFilter(CategoryFilterDTO filter, Pageable pageable) {
        return repository.findByFilter(
                        filter.getName(),
                        filter.getIsActive(),
                        pageable)
                .map(CategoryMapper::toGrid);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toDTO(entity);
    }

    @Transactional
    public CategoryDTO save(CategoryFormDTO form) {
        Category entity = CategoryMapper.toEntity(form);
        entity = repository.save(entity);
        return CategoryMapper.toDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryFormDTO form) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Category updated = CategoryMapper.toEntity(form);
        updated.setId(id);
        entity = repository.save(updated);
        return CategoryMapper.toDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        repository.delete(entity);
    }

    @Transactional
    public CategoryDTO toggleAtivo(Long id, Boolean flag) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        entity.setIsActive(flag);
        entity = repository.save(entity);
        return CategoryMapper.toDTO(entity);
    }
}