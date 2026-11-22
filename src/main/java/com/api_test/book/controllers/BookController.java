package com.api_test.book.controllers;

import com.api_test.book.dto.BookDTO;
import com.api_test.book.dto.BookFilterDTO;
import com.api_test.book.dto.BookFormDTO;
import com.api_test.book.dto.BookGridDTO;
import com.api_test.book.service.BookService;
import com.api_test.book.storage.ImageStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final ImageStorageService imageStorageService;

    public BookController(BookService bookService, ImageStorageService imageStorageService) {
        this.bookService = bookService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public ResponseEntity<Page<BookGridDTO>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return ResponseEntity.ok(bookService.findAtivos(pageRequest));
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<BookGridDTO>> findAtivos(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(bookService.findAtivos(pageRequest));
    }

    @GetMapping("/destaques")
    public ResponseEntity<List<BookGridDTO>> findDestaques(
            @RequestParam(defaultValue = "8") int limit) {

        PageRequest pageRequest = PageRequest.of(0, limit);
        return ResponseEntity.ok(bookService.findDestaques(pageRequest).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<BookGridDTO>> search(@RequestBody BookFilterDTO filter,
                                                    @RequestParam(defaultValue = "0") int pageNumber,
                                                    @RequestParam(defaultValue = "10") int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(bookService.findByFilter(filter, pageRequest));
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookFormDTO form) {
        BookDTO result = bookService.save(form);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookFormDTO form) {
        BookDTO result = bookService.update(id, form);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<BookDTO> toggleAtivo(@PathVariable Long id, @RequestBody Boolean flag) {
        BookDTO result = bookService.toggleAtivo(id, flag);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/imagem")
    public ResponseEntity<BookDTO> uploadImagem(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String imagemPath = imageStorageService.store(file);
        BookDTO book = bookService.findById(id);
        BookFormDTO form = new BookFormDTO();
        form.setId(book.getId());
        form.setTitle(book.getTitle());
        form.setAuthor(book.getAuthor());
        form.setText(book.getText());
        form.setYearPublication(book.getYearPublication());
        form.setPrice(book.getPrice());
        form.setImagem(imagemPath);
        form.setAtivo(book.getAtivo());
        form.setCategoryId(book.getCategoryId());
        BookDTO updated = bookService.update(id, form);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> getImagem(@PathVariable Long id) {
        BookDTO book = bookService.findById(id);
        if (book.getImagem() == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            Path path = Path.of(book.getImagem().substring(1));
            byte[] bytes = Files.readAllBytes(path);
            String contentType = Files.probeContentType(path);
            return ResponseEntity.ok().header("Content-Type", contentType != null ? contentType : "application/octet-stream").body(bytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}