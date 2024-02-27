package com.example.Backendmed21.entities;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MediaResponse {
    private Long id;
    private String legende;
    @Temporal(TemporalType.DATE)
    private LocalDate date_video;
    @Lob
    private byte[] video;
}
