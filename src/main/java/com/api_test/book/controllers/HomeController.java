package com.api_test.book.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
            "message", "Library API - Backend REST",
            "documentation", "/swagger-ui.html",
            "status", "running"
        );
    }
}