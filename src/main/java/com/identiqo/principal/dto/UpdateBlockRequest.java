package com.identiqo.principal.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateBlockRequest {
    private UUID profileBlockId;
    private String content; // JSON string
}