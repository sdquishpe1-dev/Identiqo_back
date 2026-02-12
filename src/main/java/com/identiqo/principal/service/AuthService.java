package com.identiqo.principal.service;

import com.identiqo.principal.dto.*;
import com.identiqo.principal.model.*;
import com.identiqo.principal.repository.UserAuthProviderRepository;
import com.identiqo.principal.repository.UserRepository;
import com.identiqo.principal.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserAuthProviderRepository authProviderRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final EmailVerificationService emailVerificationService;
    private final GoogleAuthService googleAuthService;

    // ========================================
    // REGISTRO LOCAL
    // ========================================
    public AuthResponse register(RegisterRequest request) {

        var existingUserOpt = userRepository.findByEmail(request.getEmail());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            boolean hasLocal = authProviderRepository
                    .findByProviderAndUser(AuthProvider.LOCAL, existingUser)
                    .isPresent();
            if (hasLocal) {
                throw new RuntimeException("Usuario ya registrado con contraseña");
            } else {
                throw new RuntimeException("Este email ya existe con Google. Usa login con Google");
            }
        }

        // Crear usuario
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true) // email verification pendiente cambiar cuando se tenga servidor de correos
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        // Crear proveedor LOCAL
        UserAuthProvider localProvider = UserAuthProvider.builder()
                .user(user)
                .provider(AuthProvider.LOCAL)
                .providerId(null)
                .enabled(true)
                .build();

        authProviderRepository.save(localProvider);

        // Crear token de verificación de email
        String token = emailVerificationService.createToken(user);
        emailService.sendVerificationEmail(user.getEmail(), token);

        return new AuthResponse(token);
    }

    // ========================================
    // LOGIN LOCAL
    // ========================================
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar si tiene provider LOCAL
        UserAuthProvider localProvider = authProviderRepository
                .findByProviderAndUser(AuthProvider.LOCAL, user)
                .orElseThrow(() -> new RuntimeException("Usuario no registrado con contraseña"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Usuario no verificado");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    // ========================================
    // LOGIN / REGISTRO GOOGLE
    // ========================================
    public AuthResponse googleLogin(String idToken) {

        var payload = googleAuthService.verifyToken(idToken);

        String email = payload.getEmail();
        String googleSub = payload.getSubject(); // sub único de Google
        String name = (String) payload.get("name");

        // Verificar si ya existe el provider GOOGLE
        UserAuthProvider googleProvider = authProviderRepository
                .findByProviderAndProviderId(AuthProvider.GOOGLE, googleSub)
                .orElse(null);

        if (googleProvider != null) {
            // Usuario ya existe → devolver JWT
            String token = jwtUtil.generateToken(googleProvider.getUser().getEmail());
            return new AuthResponse(token);
        }

        // Si no existe, crear usuario o asociar existente
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = User.builder()
                    .email(email)
                    .role(Role.USER)
                    .enabled(true) // Google confiable, ya verificado
                    .createdAt(LocalDateTime.now())
                    .build();
            return userRepository.save(newUser);
        });

        // Crear provider GOOGLE
        UserAuthProvider newGoogleProvider = UserAuthProvider.builder()
                .user(user)
                .provider(AuthProvider.GOOGLE)
                .providerId(googleSub)
                .enabled(true)
                .build();

        authProviderRepository.save(newGoogleProvider);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
    public void verifyEmailToken(String token) {
        emailVerificationService.verifyToken(token);
    }

}
