package com.aidana.game;

import com.aidana.game.security.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("Успешный запрос с валидным JWT-токеном (200 OK)")
    void getHero_WithValidJwt_ShouldReturnOk() throws Exception {
        UserDetails userDetails = new User(
                "testuser",
                "password",
                Collections.emptyList()
        );

        String token = jwtService.generateToken(userDetails);

        mockMvc.perform(get("/api/v1/heroes/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Запрос с недействительным (просроченным или поддельным) токеном (401 Unauthorized)")
    void getHero_WithInvalidJwt_ShouldReturnUnauthorized() throws Exception {
        String invalidToken = "eyJhbGciOiJIUzI1NiJ9.invalid.signature";

        mockMvc.perform(get("/api/v1/heroes/1")
                        .header("Authorization", "Bearer " + invalidToken))
                .andExpect(status().isUnauthorized());
    }
}
