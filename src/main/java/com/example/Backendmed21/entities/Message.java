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
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column (name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column (name="mail")
    private String mail;
    @Column (name="contenue")
    private String contenue;



}
