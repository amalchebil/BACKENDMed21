package com.example.Backendmed21.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrixdecernerResponse {
    private Long id;
    private String nom_prix;
    private String ojectif;
    @Temporal(TemporalType.DATE)
    private Date dateprix;
    private String lieu;
    @ElementCollection
    private List<String> membresfondateurs;
    private String personnage;
    private String ceremonie;
    @ElementCollection
    private List<String> laureats;
    @ElementCollection
    private List<String> liens;
    @Lob
    private byte[] image;
}
