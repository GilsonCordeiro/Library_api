package com.api_test.book.config;

import com.api_test.book.config.JwtAuthRequest;
import com.api_test.book.config.JwtAuthResponse;
import com.api_test.book.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdminUserService adminUserService;

    public AuthController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {
        JwtAuthResponse response = adminUserService.login(request);
        return ResponseEntity.ok(response);
    }
}