package com.example.Backendmed21.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prixdecerner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    private Blob image;


/*•	Nom du Prix
•	Objectif du Prix
•	Date et lieu de création
•	Membres fondateurs
•	Personnalité historique emblématique
•	Cérémonies de remise du Prix
•	Lauréats
•	Liens
•	Photo
*/

}
