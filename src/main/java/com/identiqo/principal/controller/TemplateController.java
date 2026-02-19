package com.identiqo.principal.controller;

import com.identiqo.principal.dto.TemplateDto;
import com.identiqo.principal.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @PostMapping
    public UUID crearTemplate(@RequestBody TemplateDto templateDto)
    {

        System.out.println(templateDto);
        return templateService.guardarPlantilla(templateDto);

    }
    @GetMapping("/")
    public List<TemplateDto> findAll() {
        return templateService.recuperarPlantillas();
    }
}

