package com.identiqo.principal.controller;

import com.identiqo.principal.dto.*;
import com.identiqo.principal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ========================================
    // Registro LOCAL
    // ========================================
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    // ========================================
    // Login LOCAL
    // ========================================
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // ========================================
    // Login GOOGLE
    // ========================================
    @PostMapping("/google")
    public AuthResponse googleLogin(@RequestBody GoogleAuthRequest request) {
        return authService.googleLogin(request.getIdToken());
    }

    // ========================================
    // Verificación de email
    // ========================================
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String token) {
        authService.verifyEmailToken(token);
        return "Email verificado correctamente";
    }
}
