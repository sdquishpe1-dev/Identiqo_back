package com.identiqo.principal.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class TemplateBlockDto {
    private UUID id;
    private UUID id_plantilla;
    private String tipoBloque;
    private Integer posicion;
    private boolean esRequerido;

}
