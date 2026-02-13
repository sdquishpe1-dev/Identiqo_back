package com.identiqo.principal.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TemplateDto {
    private UUID id;
    private String nombre;
    private boolean esPremium;
    private String imagenPrevia;
}
