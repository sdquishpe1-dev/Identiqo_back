package com.identiqo.principal.repository;

import com.identiqo.principal.model.Template;
import com.identiqo.principal.model.TemplateBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TemplateBlockRepository extends JpaRepository<TemplateBlock, UUID> {
    List<TemplateBlock> findByTemplateOrderByPositionAsc(Template template);
}
