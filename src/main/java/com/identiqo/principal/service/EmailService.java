package com.identiqo.principal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    public void sendVerificationEmail(String to, String token) {
        // Aquí integras JavaMailSender o cualquier servicio SMTP
        String link = "https://identiqo.com/verify-email?token=" + token;
        System.out.println("Enviar email a " + to + " con link: " + link);
    }
}
