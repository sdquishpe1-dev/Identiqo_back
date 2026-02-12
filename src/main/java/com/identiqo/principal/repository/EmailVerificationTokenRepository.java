package com.identiqo.principal.repository;

import com.identiqo.principal.model.EmailVerificationToken;
import com.identiqo.principal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, UUID> {

    Optional<EmailVerificationToken> findByToken(String token);

    Optional<EmailVerificationToken> findByUser(User user);
}