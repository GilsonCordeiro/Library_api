package com.api_test.book.repositories;

import com.api_test.book.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findByUsername(String username);

    @Query("SELECT u FROM AdminUser u WHERE u.username = :username")
    Optional<AdminUser> findWithRoles(@Param("username") String username);
}