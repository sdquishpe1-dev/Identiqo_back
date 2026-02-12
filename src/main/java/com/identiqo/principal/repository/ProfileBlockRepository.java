package com.identiqo.principal.repository;

import com.identiqo.principal.model.Profile;
import com.identiqo.principal.model.ProfileBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProfileBlockRepository extends JpaRepository<ProfileBlock, UUID> {
    List<ProfileBlock> findByProfile(Profile profile);
}
