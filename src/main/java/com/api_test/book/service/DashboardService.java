package com.api_test.book.service;

import com.api_test.book.dto.DashboardStatsDTO;
import com.api_test.book.repositories.BookRepository;
import com.api_test.book.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public DashboardService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public DashboardStatsDTO getStats() {
        return new DashboardStatsDTO(
                bookRepository.count(),
                bookRepository.countByAtivoTrue(),
                bookRepository.countByAtivoFalse(),
                categoryRepository.count());
    }
}
