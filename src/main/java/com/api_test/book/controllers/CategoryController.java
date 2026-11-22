package com.api_test.book.controllers;

import com.api_test.book.dto.CategoryDTO;
import com.api_test.book.dto.CategoryFilterDTO;
import com.api_test.book.dto.CategoryFormDTO;
import com.api_test.book.dto.CategoryGridDTO;
import com.api_test.book.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<CategoryGridDTO>> filter(@RequestBody CategoryFilterDTO filter,
                                                         @RequestParam(defaultValue = "0") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(categoryService.findByFilter(filter, pageRequest));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryFormDTO form) {
        CategoryDTO result = categoryService.save(form);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryFormDTO form) {
        CategoryDTO result = categoryService.update(id, form);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CategoryDTO> toggleAtivo(@PathVariable Long id, @RequestBody Boolean flag) {
        CategoryDTO result = categoryService.toggleAtivo(id, flag);
        return ResponseEntity.ok(result);
    }
}