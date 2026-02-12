package com.identiqo.principal.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ToggleBlockRequest {
    private UUID profileBlockId;
    private boolean visible;
}
