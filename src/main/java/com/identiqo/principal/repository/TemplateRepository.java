package com.identiqo.principal.repository;

import com.identiqo.principal.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemplateRepository extends JpaRepository<Template, UUID> {
}
