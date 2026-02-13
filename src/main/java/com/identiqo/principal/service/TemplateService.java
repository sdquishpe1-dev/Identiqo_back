package com.identiqo.principal.service;

import com.identiqo.principal.dto.TemplateDto;
import com.identiqo.principal.mapper.TemplateMapper;
import com.identiqo.principal.model.Template;
import com.identiqo.principal.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;
    //@Override
    public void guardarPlantilla(TemplateDto dto){
        Template entity = TemplateMapper.toEntity(dto);
        templateRepository.save(entity);

    }

}
