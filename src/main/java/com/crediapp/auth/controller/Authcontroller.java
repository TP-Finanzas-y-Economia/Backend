package com.crediapp.auth.controller;
import com.crediapp.auth.dto.*;
import com.crediapp.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.crediapp.auth.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class Authcontroller {
    private final AuthService authService;
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/signup")
    public AuthResponse register(@RequestBody SignupRequest request) {
        return authService.register(request);
    }
}
