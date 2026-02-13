package com.identiqo.principal.mapper;

import com.identiqo.principal.dto.TemplateBlockDto;
import com.identiqo.principal.dto.TemplateDto;
import com.identiqo.principal.model.Template;
import com.identiqo.principal.model.TemplateBlock;

public class TemplateBlockMapper {

    public static TemplateBlockDto toDto(TemplateBlock entity) {
        if (entity == null) return null;

        TemplateBlockDto dto = new TemplateBlockDto();
        dto.setId(entity.getId());

        // Verificamos que la relación no sea nula para evitar NullPointerException
        if (entity.getTemplate() != null) {
            dto.setId_plantilla(entity.getTemplate().getId());
        }

        dto.setTipoBloque(entity.getBlockType());
        dto.setPosicion(entity.getPosition());
        dto.setEsRequerido(entity.isRequired());
        return dto;
    }

    public static TemplateBlock toEntity(TemplateBlockDto dto) {
        if (dto == null) return null;

        TemplateBlock entity = new TemplateBlock();
        entity.setId(dto.getId());

        // Creamos una instancia "proxy" de Template solo con el ID
        if (dto.getId_plantilla() != null) {
            Template template = new Template();
            template.setId(dto.getId_plantilla());
            entity.setTemplate(template);
        }

        entity.setBlockType(dto.getTipoBloque());
        entity.setPosition(dto.getPosicion());
        entity.setRequired(dto.isEsRequerido());

        return entity; // No olvides el return
    }
}
