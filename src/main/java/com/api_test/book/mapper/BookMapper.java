package com.api_test.book.mapper;

import com.api_test.book.dto.BookDTO;
import com.api_test.book.dto.BookFormDTO;
import com.api_test.book.dto.BookGridDTO;
import com.api_test.book.model.Book;

public final class BookMapper {

    private BookMapper() {}

    public static Book toEntity(BookFormDTO form) {
        if (form == null) return null;
        Book entity = new Book();
        entity.setId(form.getId());
        entity.setTitle(form.getTitle());
        entity.setAuthor(form.getAuthor());
        entity.setText(form.getText());
        entity.setYearPublication(form.getYearPublication());
        entity.setPrice(form.getPrice());
        entity.setImagem(form.getImagem());
        entity.setAtivo(form.getAtivo() != null ? form.getAtivo() : true);
        return entity;
    }

    public static BookFormDTO toForm(Book entity) {
        if (entity == null) return null;
        BookFormDTO form = new BookFormDTO();
        form.setId(entity.getId());
        form.setTitle(entity.getTitle());
        form.setAuthor(entity.getAuthor());
        form.setText(entity.getText());
        form.setYearPublication(entity.getYearPublication());
        form.setPrice(entity.getPrice());
        form.setImagem(entity.getImagem());
        form.setAtivo(entity.getAtivo());
        return form;
    }

    public static BookGridDTO toGrid(Book entity) {
        if (entity == null) return null;
        BookGridDTO grid = new BookGridDTO();
        grid.setId(entity.getId());
        grid.setTitle(entity.getTitle());
        grid.setAuthor(entity.getAuthor());
        grid.setCategoryName(entity.getCategory() != null ? entity.getCategory().getName() : null);
        grid.setPrice(entity.getPrice());
        grid.setAtivo(entity.getAtivo());
        grid.setImagem(entity.getImagem());
        return grid;
    }

    public static BookDTO toDTO(Book entity) {
        if (entity == null) return null;
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setText(entity.getText());
        dto.setYearPublication(entity.getYearPublication());
        dto.setPrice(entity.getPrice());
        dto.setImagem(entity.getImagem());
        dto.setAtivo(entity.getAtivo());
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCategoryName(entity.getCategory() != null ? entity.getCategory().getName() : null);
        return dto;
    }
}