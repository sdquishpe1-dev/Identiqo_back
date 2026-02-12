package com.identiqo.principal.service;


import com.identiqo.principal.model.EmailVerificationToken;
import com.identiqo.principal.model.User;
import com.identiqo.principal.repository.EmailVerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;

    // Crear token y devolverlo (para enviar por email)
    public String createToken(User user) {
        String token = UUID.randomUUID().toString();

        EmailVerificationToken verificationToken = EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .used(false)
                .build();

        tokenRepository.save(verificationToken);
        return token;
    }

    // Validar token
    public void verifyToken(String token) {
        EmailVerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (verificationToken.isUsed()) {
            throw new RuntimeException("Token ya usado");
        }

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        // Marcar como usado
        verificationToken.setUsed(true);
        tokenRepository.save(verificationToken);

        // Activar usuario
        User user = verificationToken.getUser();
        user.setEnabled(true); // <-- Nuevo campo en User
    }

}
