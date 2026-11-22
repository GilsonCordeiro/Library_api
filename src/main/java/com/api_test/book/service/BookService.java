package com.api_test.book.service;

import com.api_test.book.dto.BookDTO;
import com.api_test.book.dto.BookFilterDTO;
import com.api_test.book.dto.BookFormDTO;
import com.api_test.book.dto.BookGridDTO;
import com.api_test.book.mapper.BookMapper;
import com.api_test.book.model.Book;
import com.api_test.book.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<BookGridDTO> findAtivos(Pageable pageable) {
        return repository.findAtivos(pageable).map(BookMapper::toGrid);
    }

    @Transactional(readOnly = true)
    public Page<BookGridDTO> findDestaques(Pageable pageable) {
        return repository.findDestaques(pageable).map(BookMapper::toGrid);
    }

    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return BookMapper.toDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<BookGridDTO> findByFilter(BookFilterDTO filter, Pageable pageable) {
        return repository.findByFilter(
                        filter.getTitle(),
                        filter.getAuthor(),
                        filter.getCategoryId(),
                        filter.getYear(),
                        filter.getMinPrice(),
                        filter.getMaxPrice(),
                        pageable)
                .map(BookMapper::toGrid);
    }

    @Transactional
    public BookDTO save(BookFormDTO form) {
        Book entity = BookMapper.toEntity(form);
        entity = repository.save(entity);
        return BookMapper.toDTO(entity);
    }

    @Transactional
    public BookDTO update(Long id, BookFormDTO form) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Book updated = BookMapper.toEntity(form);
        updated.setId(id);
        // Copy non-null fields from updated to entity
        if (updated.getTitle() != null) entity.setTitle(updated.getTitle());
        if (updated.getAuthor() != null) entity.setAuthor(updated.getAuthor());
        if (updated.getText() != null) entity.setText(updated.getText());
        if (updated.getYearPublication() != null) entity.setYearPublication(updated.getYearPublication());
        if (updated.getPrice() != null) entity.setPrice(updated.getPrice());
        if (updated.getImagem() != null) entity.setImagem(updated.getImagem());
        if (updated.getAtivo() != null) entity.setAtivo(updated.getAtivo());
        if (updated.getCategory() != null) entity.setCategory(updated.getCategory());
        entity = repository.save(entity);
        return BookMapper.toDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        repository.deleteById(id);
    }

    @Transactional
    public BookDTO toggleAtivo(Long id, Boolean flag) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        entity.setAtivo(flag);
        entity = repository.save(entity);
        return BookMapper.toDTO(entity);
    }
}