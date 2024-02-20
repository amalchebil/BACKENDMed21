package com.example.Backendmed21.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrixavenirResponse {
    private Long id;
    private String nom_prix;
    private String description;
    @Temporal(TemporalType.DATE)
    private LocalDate datepub;
    @Lob
    private byte[] image;
    private Prixdecerner historique;
}
