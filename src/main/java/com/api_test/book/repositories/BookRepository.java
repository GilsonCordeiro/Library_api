package com.api_test.book.repositories;

import com.api_test.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.ativo = true " +
            "AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) " +
            "AND (:categoryId IS NULL OR b.category.id = :categoryId) " +
            "AND (:year IS NULL OR b.yearPublication = :year) " +
            "AND (:minPrice IS NULL OR b.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR b.price <= :maxPrice)")
    Page<Book> findByFilter(@Param("title") String title,
                            @Param("author") String author,
                            @Param("categoryId") Long categoryId,
                            @Param("year") Integer year,
                            @Param("minPrice") BigDecimal minPrice,
                            @Param("maxPrice") BigDecimal maxPrice,
                            Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.ativo = true ORDER BY b.createdAt DESC")
    Page<Book> findAtivos(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.ativo = true ORDER BY RAND()")
    Page<Book> findDestaques(Pageable pageable);
}