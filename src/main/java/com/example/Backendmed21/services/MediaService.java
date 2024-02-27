package com.example.Backendmed21.services;

import com.example.Backendmed21.entities.Media;
import com.example.Backendmed21.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MediaService {
    @Autowired
    private MediaRepository clt;
    public Media AjouterMedia(Media f) {
        f.setDate_video(LocalDate.now());
        return clt.save(f);
    }

    public Media ModifierMedia(Media f) {
        return clt.save(f);
    }

    public void deleteMedia(Long id) {
        Media Media = getMediaById(id);
        clt.delete(Media);
    }

    public List<Media> ListMedia() {
        return clt.findAll();
    }

    public Optional<Media> AfficherMedia(Long id) {
        return clt.findById(id);
    }

    public Media getMediaById(long id) {
        Optional<Media> Media = clt.findById(id); // Récupère le Media correspondante dans la base de données par son ID
        if (Media.isPresent()) {
            return Media.get(); // Retourne le Media si elle existe
        } else {
            throw new NoSuchElementException("Media introuvable pour l'ID: " + id); // Lance une exception si le Media n'existe pas
        }
    }
}
