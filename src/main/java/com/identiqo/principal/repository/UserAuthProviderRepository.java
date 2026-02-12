package com.identiqo.principal.repository;

import com.identiqo.principal.model.UserAuthProvider;
import com.identiqo.principal.model.User;
import com.identiqo.principal.model.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthProviderRepository extends JpaRepository<UserAuthProvider, String> {

    Optional<UserAuthProvider> findByProviderAndProviderId(AuthProvider provider, String providerId);

    Optional<UserAuthProvider> findByProviderAndUser(AuthProvider provider, User user);

}