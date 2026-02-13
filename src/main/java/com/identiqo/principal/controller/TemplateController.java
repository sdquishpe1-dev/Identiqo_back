package com.identiqo.principal.controller;

import com.identiqo.principal.dto.TemplateDto;
import com.identiqo.principal.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @PostMapping
    public void crearTemplate(@RequestBody TemplateDto templateDto)
    {
        templateService.guardarPlantilla(templateDto);
    }
}

