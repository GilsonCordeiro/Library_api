package com.api_test.book.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider tokenProvider;

    @BeforeEach
    void setUp() {
        tokenProvider = new JwtTokenProvider();
        ReflectionTestUtils.setField(tokenProvider, "secret", "myVerySecretKeyForJWTTokenGenerationAndSigning2024BibliotecaDigitalSecurityKey512Bits!");
        ReflectionTestUtils.setField(tokenProvider, "expirationMs", 86400000L);
        tokenProvider.init();
    }

    @Test
    void shouldGenerateAndValidateTokenSuccessfully() {
        String token = tokenProvider.generateToken("admin", List.of("ADMIN"));
        assertNotNull(token);
        assertTrue(token.length() > 20);

        assertTrue(tokenProvider.validateToken(token));
        assertEquals("admin", tokenProvider.extractUsername(token));
        assertEquals(List.of("ADMIN"), tokenProvider.extractRoles(token));
    }
}
