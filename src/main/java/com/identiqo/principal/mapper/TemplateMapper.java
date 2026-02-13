package com.identiqo.principal.mapper;

import com.identiqo.principal.dto.TemplateDto;
import com.identiqo.principal.model.Template;

public class TemplateMapper {
    public static TemplateDto toDto(Template template) {
        TemplateDto templateDto = new TemplateDto();
        templateDto.setId(template.getId());
        templateDto.setNombre(template.getName());
        templateDto.setEsPremium(template.isPremium());
        templateDto.setImagenPrevia(templateDto.getImagenPrevia());
        return templateDto;
    }
    public static Template toEntity(TemplateDto templateDto) {
        Template entity = new Template();
        entity.setId(templateDto.getId());
        entity.setName(templateDto.getNombre());
        entity.setPremium(templateDto.isEsPremium());
        entity.setPreviewImage(templateDto.getImagenPrevia());
        return entity;
    }
}

