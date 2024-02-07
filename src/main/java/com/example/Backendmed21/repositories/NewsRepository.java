package com.example.Backendmed21.repositories;

import com.example.Backendmed21.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Long> {
}
