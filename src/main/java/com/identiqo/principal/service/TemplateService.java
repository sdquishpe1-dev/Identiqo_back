package com.identiqo.principal.service;

import com.identiqo.principal.dto.TemplateDto;
import com.identiqo.principal.mapper.TemplateMapper;
import com.identiqo.principal.model.Template;
import com.identiqo.principal.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;
    //@Override
    public UUID guardarPlantilla(TemplateDto dto){
        Template entity = TemplateMapper.toEntity(dto);
        templateRepository.save(entity);
        return entity.getId();
    }
    public List<TemplateDto> recuperarPlantillas() {
        List<Template> entity = templateRepository.findAll();
        return TemplateMapper.toDto(entity);
    }
}
