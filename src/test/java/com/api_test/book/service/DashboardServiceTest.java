package com.api_test.book.service;

import com.api_test.book.dto.DashboardStatsDTO;
import com.api_test.book.repositories.BookRepository;
import com.api_test.book.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void getStatsReturnsBookAndCategoryCounts() {
        when(bookRepository.count()).thenReturn(10L);
        when(bookRepository.countByAtivoTrue()).thenReturn(7L);
        when(bookRepository.countByAtivoFalse()).thenReturn(3L);
        when(categoryRepository.count()).thenReturn(4L);

        DashboardStatsDTO stats = dashboardService.getStats();

        assertEquals(10L, stats.getTotalBooks());
        assertEquals(7L, stats.getActiveBooks());
        assertEquals(3L, stats.getInactiveBooks());
        assertEquals(4L, stats.getTotalCategories());
    }
}
