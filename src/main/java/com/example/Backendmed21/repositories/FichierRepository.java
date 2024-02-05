package com.example.Backendmed21.repositories;

import com.example.Backendmed21.entities.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierRepository extends JpaRepository<Fichier,Long> {
}
