package com.danielmonsalve.permitflow.config;


import com.danielmonsalve.permitflow.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones, solo JWT
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas (Login y Swagger)
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                        // Rutas protegidas por ROLES (RBAC)
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/solicitudes").hasRole("CIUDADANO")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/solicitudes/**").hasRole("FUNCIONARIO")
                        .requestMatchers("/api/solicitudes/**").hasAnyRole("CIUDADANO", "FUNCIONARIO", "ADMIN")

                        .anyRequest().authenticated()
                )
                // Agregamos nuestro filtro JWT antes del filtro por defecto de Spring!
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
