package com.project.NutritionApp.config;


import com.project.NutritionApp.security.JWTAuthenticationEntryPoint;
import com.project.NutritionApp.security.JWTAuthenticationFilter;
import com.project.NutritionApp.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomCorsConfiguration customCorsConfiguration;

    private UserDetailService userDetailsService;

    private JWTAuthenticationEntryPoint handler;

    public SecurityConfig(UserDetailService userDetailsService, JWTAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

  /*  @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Farklı bir domain kullanıyorsanız buna göre değiştirin
        config.addAllowedHeader("*"); // Authorization dahil
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    } OPTİONAL CORS CONFIG */


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF korumasını devre dışı bırakıyoruz (isteğe bağlı)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(handler) // Yetkisiz erişim durumunda özel yanıt
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT ile stateless oturum
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register","/*.jpg","/uploads/**").permitAll() // İzin verilen uç noktalar
                        .anyRequest().authenticated() // Diğer tüm uç noktalar için kimlik doğrulama
                )
                .cors(c -> c.configurationSource(customCorsConfiguration))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // JWT doğrulama filtresi

        return http.build();
    }
}