package com.identiqo.principal.repository;

import com.identiqo.principal.model.Profile;
import com.identiqo.principal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Optional<Profile> findByUsername(String username);
    Optional<List<Profile>> findProfilesByUser_Id(UUID id);

    @Override
    boolean existsById(UUID uuid);

    boolean existsProfileByUser_Id(UUID userId);
}
