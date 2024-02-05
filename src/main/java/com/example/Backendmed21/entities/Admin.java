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
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom_admin")
    private String nom_admin;

    @Column(name="prenom_admin")
    private String prenom_admin;

    @Column (name="mail_admin")
    private String mail_admin;
    @Column (name="password_admin")
    private String password_admin;
}
