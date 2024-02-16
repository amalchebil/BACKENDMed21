package com.example.Backendmed21.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Discour {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="titre")
    private String titre;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_disc", nullable = false)
    private Date date_disc;
    @Column (name="discour")
    private String discour;

}
