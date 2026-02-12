package com.identiqo.principal.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "user_auth_providers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider; // LOCAL, GOOGLE, MICROSOFT...

    @Column(unique = true)
    private String providerId; // Para Google/Microsoft → el sub/id del OAuth

    @Column(nullable = false)
    private boolean enabled = true; // Si la cuenta está activa
}