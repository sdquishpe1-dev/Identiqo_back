package com.identiqo.principal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "template_blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    private String blockType; // hero, about, services, etc.

    private Integer position;

    private boolean isRequired;
}