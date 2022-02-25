package com.api_test.book.services;

import com.api_test.book.exception.ObjectNotFoundException;
import com.api_test.book.model.Category;
import com.api_test.book.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServices {
    @Autowired
    CategoryRepository repository;
    public Category findById(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" +id + ", Tipo: " + Category.class.getName()));
    }
}
