package com.example.Backendmed21.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class News {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="titre")
    private String titre;
    @Column (name="description")
    private String description;
    @Temporal(TemporalType.DATE)
    private LocalDate date_news;
 @Lob
   private Blob image;

   /* @JsonIgnore
    @OneToMany(mappedBy = "news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fichier> fichiers;*/

}
