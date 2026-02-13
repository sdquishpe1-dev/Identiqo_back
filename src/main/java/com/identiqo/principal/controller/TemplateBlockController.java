package com.identiqo.principal.controller;

import com.identiqo.principal.dto.TemplateBlockDto;
import com.identiqo.principal.dto.TemplateDto;
import com.identiqo.principal.service.TemplateBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/template-block")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TemplateBlockController {
    @Autowired
    private TemplateBlockService templateBlockService;

    @PostMapping
    public void crearTemplateBlock(@RequestBody TemplateBlockDto templateDto){templateBlockService.guardarBloquesPlantilla(templateDto);}
}
