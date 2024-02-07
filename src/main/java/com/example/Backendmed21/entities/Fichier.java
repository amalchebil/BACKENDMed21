package com.example.Backendmed21.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fichier")
    private Long id_ficher;
    @Column(name = "titre")
    private String titre;

    @Column(name = "type")
    private String fileType;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_news")
    private News news;
    public Fichier(String titre, String fileType, byte[] data, News news1) {
        this.titre = titre;
        this.fileType = fileType;
        this.data = data;
        this.news = news1;
    }
}
