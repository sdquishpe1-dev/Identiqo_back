package com.identiqo.principal.controller;

import com.identiqo.principal.dto.UpdateBlockRequest;
import com.identiqo.principal.model.Profile;
import com.identiqo.principal.repository.ProfileBlockRepository;
import com.identiqo.principal.repository.ProfileRepository;
import com.identiqo.principal.repository.TemplateBlockRepository;
import com.identiqo.principal.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PublicProfileController {

    private final ProfileRepository profileRepository;
    private final TemplateBlockRepository templateBlockRepository;
    private final ProfileBlockRepository profileBlockRepository;

    @GetMapping("/u/{username}")
    public Map<String, Object> getPublicProfile(@PathVariable String username) {

        Profile profile = profileRepository.findByUsername(username)
                .orElseThrow();

        var templateBlocks =
                templateBlockRepository.findByTemplateOrderByPositionAsc(profile.getTemplate());

        var profileBlocks =
                profileBlockRepository.findByProfile(profile);

        return Map.of(
                "profile", profile,
                "templateBlocks", templateBlocks,
                "profileBlocks", profileBlocks
        );
    }


}
