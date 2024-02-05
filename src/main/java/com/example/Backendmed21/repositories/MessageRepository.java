package com.example.Backendmed21.repositories;

import com.example.Backendmed21.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
