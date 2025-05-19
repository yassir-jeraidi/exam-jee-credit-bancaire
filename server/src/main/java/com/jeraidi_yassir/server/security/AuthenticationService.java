package com.jeraidi_yassir.server.security;

import com.jeraidi_yassir.server.dtos.LoginRequest;
import com.jeraidi_yassir.server.dtos.LoginResponse;
import com.jeraidi_yassir.server.entities.User;
import com.jeraidi_yassir.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse authenticate(LoginRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    }

    public LoginResponse refreshToken(String refreshToken) {
        var user = userRepository.findByEmail(jwtService.getEmailFromToken(refreshToken))
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));
        var jwtToken = jwtService.generateToken(user);
        var newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(newRefreshToken)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    public Boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}