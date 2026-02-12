package com.identiqo.principal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private boolean isPremium;

    private String previewImage;
}