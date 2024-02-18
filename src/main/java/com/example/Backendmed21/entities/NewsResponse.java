package com.example.Backendmed21.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewsResponse {
    private Long id;
    private String title;
    private String description;
    private byte[] image; // Si vous préférez renvoyer l'image sous forme de tableau de bytes

    // Constructeurs, getters et setters

    public NewsResponse(Long id, String title, String description, byte[] image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }
}
