package com.danielmonsalve.permitflow.service;

import com.danielmonsalve.permitflow.dto.AuthResponseDTO;
import com.danielmonsalve.permitflow.dto.LoginRequestDTO;
import com.danielmonsalve.permitflow.repository.UsuarioRepository;
import com.danielmonsalve.permitflow.security.CustomUserDetailsService;
import com.danielmonsalve.permitflow.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;


    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);

        return AuthResponseDTO.builder()
                .token(token)
                .email(userDetails.getUsername())
                .rol(userDetails.getAuthorities().iterator().next().getAuthority())
                .build();
    }
}
