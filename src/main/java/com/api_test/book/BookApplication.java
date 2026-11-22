package com.api_test.book;

import com.api_test.book.service.AdminUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(AdminUserService adminUserService) {
        return args -> {
            adminUserService.seedAdmin();
        };
    }
}