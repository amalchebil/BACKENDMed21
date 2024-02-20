package com.example.Backendmed21.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prixavenir {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nom_prix;
    private String description;
    @Temporal(TemporalType.DATE)
    private LocalDate datepub;
    @Lob
    private Blob image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "historique")
    private Prixdecerner historique;
}
