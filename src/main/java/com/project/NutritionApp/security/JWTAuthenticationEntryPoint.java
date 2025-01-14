package com.project.NutritionApp.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint  {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Yanıta özel bir mesaj ekleniyor
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON formatında bir mesaj döndürmek
        String jsonMessage = String.format("{\"error\": \"Unauthorized\", \"message\": \"%s\"}", "Lütfen giriş yapınız.");

        // Yanıta yazılıyor
        response.getWriter().write(jsonMessage);
    }
}