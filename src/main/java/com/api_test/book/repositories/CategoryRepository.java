package com.api_test.book.repositories;

import com.api_test.book.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:isActive IS NULL OR c.isActive = :isActive)")
    Page<Category> findByFilter(@Param("name") String name,
                                @Param("isActive") Boolean isActive,
                                Pageable pageable);

    @Query("SELECT c.id FROM Category c WHERE (:id IS NULL OR c.id <> :id) AND UPPER(c.name) = UPPER(:name)")
    Optional<Long> findIdByName(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT c FROM Category c WHERE UPPER(c.name) = UPPER(:name)")
    Optional<Category> findByName(@Param("name") String name);
}