package com.identiqo.principal.service;

import com.identiqo.principal.dto.TemplateBlockDto;
import com.identiqo.principal.mapper.TemplateBlockMapper;
import com.identiqo.principal.model.TemplateBlock;
import com.identiqo.principal.repository.TemplateBlockRepository;
import com.identiqo.principal.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateBlockService {
    @Autowired
    private TemplateBlockRepository templateRepository;

    public void guardarBloquesPlantilla(TemplateBlockDto dto){
        TemplateBlock entity= TemplateBlockMapper.toEntity(dto);
        templateRepository.save(entity);
    }
}
