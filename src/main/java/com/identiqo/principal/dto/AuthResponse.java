package com.identiqo.principal.dto;

import com.identiqo.principal.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private boolean hasProfile;
    private Role role;
}