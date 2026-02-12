package com.identiqo.principal.controller;

import com.identiqo.principal.dto.ChangeTemplateRequest;
import com.identiqo.principal.dto.ToggleBlockRequest;
import com.identiqo.principal.dto.UpdateBlockRequest;
import com.identiqo.principal.model.Profile;
import com.identiqo.principal.security.JwtUtil;
import com.identiqo.principal.security.SecurityUtils;
import com.identiqo.principal.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public Profile createProfile(
            @RequestParam String username,
            @RequestParam UUID templateId,
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        return profileService.createProfile(email, username, templateId);
    }

    @PutMapping("/block")
    public void updateBlock(@RequestBody UpdateBlockRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        profileService.updateBlock(
                request.getProfileBlockId(),
                request.getContent(),
                email
        );
    }
    @PutMapping("/block/toggle")
    public void toggleBlock(@RequestBody ToggleBlockRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        profileService.toggleBlock(
                request.getProfileBlockId(),
                request.isVisible(),
                email
        );
    }
    @PutMapping("/change-template")
    public void changeTemplate(@RequestBody ChangeTemplateRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        profileService.changeTemplate(
                request.getProfileId(),
                request.getNewTemplateId(),
                email
        );
    }

}
