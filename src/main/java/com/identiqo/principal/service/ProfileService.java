package com.identiqo.principal.service;

import com.identiqo.principal.model.*;
import com.identiqo.principal.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    TemplateRepository templateRepository;
    @Autowired
    TemplateBlockRepository templateBlockRepository;
    @Autowired
    ProfileBlockRepository profileBlockRepository;
    @Autowired
    UserRepository userRepository;

    public Profile createProfile(String email, String username, UUID templateId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Template template = templateRepository.findById(templateId)
                .orElseThrow();

        Profile profile = Profile.builder()
                .username(username)
                .displayName(username)
                .user(user)
                .template(template)
                .createdAt(LocalDateTime.now())
                .build();

        profileRepository.save(profile);

        // Crear bloques iniciales
        List<TemplateBlock> templateBlocks =
                templateBlockRepository.findByTemplateOrderByPositionAsc(template);

        for (TemplateBlock tb : templateBlocks) {
            ProfileBlock pb = ProfileBlock.builder()
                    .profile(profile)
                    .templateBlock(tb)
                    .content("{}")
                    .isVisible(tb.isRequired())
                    .build();

            profileBlockRepository.save(pb);
        }

        return profile;
    }

    public void updateBlock(UUID profileBlockId, String content, String email) {

        ProfileBlock block = profileBlockRepository.findById(profileBlockId)
                .orElseThrow();

        if (!block.getProfile().getUser().getEmail().equals(email)) {
            throw new RuntimeException("No autorizado");
        }

        block.setContent(content);

        profileBlockRepository.save(block);
    }
    public void toggleBlock(UUID profileBlockId, boolean visible, String email) {

        ProfileBlock block = profileBlockRepository.findById(profileBlockId)
                .orElseThrow();

        if (!block.getProfile().getUser().getEmail().equals(email)) {
            throw new RuntimeException("No autorizado");
        }

        if (block.getTemplateBlock().isRequired() && !visible) {
            throw new RuntimeException("No puedes ocultar un bloque obligatorio");
        }

        block.setVisible(visible);

        profileBlockRepository.save(block);
    }
    @Transactional
    public void changeTemplate(UUID profileId, UUID newTemplateId, String email) {

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow();

        if (!profile.getUser().getEmail().equals(email)) {
            throw new RuntimeException("No autorizado");
        }

        Template newTemplate = templateRepository.findById(newTemplateId)
                .orElseThrow();

        profile.setTemplate(newTemplate);

        // eliminar bloques actuales
        List<ProfileBlock> oldBlocks =
                profileBlockRepository.findByProfile(profile);

        profileBlockRepository.deleteAll(oldBlocks);

        // crear nuevos bloques
        List<TemplateBlock> templateBlocks =
                templateBlockRepository.findByTemplateOrderByPositionAsc(newTemplate);

        for (TemplateBlock tb : templateBlocks) {

            ProfileBlock pb = ProfileBlock.builder()
                    .profile(profile)
                    .templateBlock(tb)
                    .content("{}")
                    .isVisible(tb.isRequired())
                    .build();

            profileBlockRepository.save(pb);
        }
    }

}
