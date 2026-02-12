package com.identiqo.principal.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChangeTemplateRequest {
    private UUID profileId;
    private UUID newTemplateId;
}
