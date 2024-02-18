package com.example.Backendmed21.repositories;

import com.example.Backendmed21.entities.News;
import com.example.Backendmed21.entities.Prixdecerner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrixdecernerRepository extends JpaRepository<Prixdecerner,Long> {
}
