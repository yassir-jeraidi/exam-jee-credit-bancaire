package com.jeraidi_yassir.server.controllers;

import com.jeraidi_yassir.server.dtos.LoginRequest;
import com.jeraidi_yassir.server.dtos.LoginResponse;
import com.jeraidi_yassir.server.entities.User;
import com.jeraidi_yassir.server.security.AuthenticationService;
import com.jeraidi_yassir.server.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest authenticationRequest) {
        System.out.println(authenticationRequest);
        try {
            LoginResponse res = authenticationService.authenticate(authenticationRequest);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(@RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7);
            String email = jwtService.getEmailFromToken(token);
            User res = authenticationService.getUserByEmail(email);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestParam("token") String refreshToken) {
        try {
            LoginResponse res = authenticationService.refreshToken(refreshToken);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
        try {
            Boolean res = authenticationService.validateToken(token);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}