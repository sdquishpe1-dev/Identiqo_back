package com.identiqo.principal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    private String displayName;
    @Enumerated(EnumType.STRING)
    private ProfileType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "parent_profile_id")
    private Profile parentProfile;


    private LocalDateTime createdAt;
}