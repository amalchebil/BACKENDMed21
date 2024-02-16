package com.example.Backendmed21.services;

import com.example.Backendmed21.entities.Discour;
import com.example.Backendmed21.entities.Message;
import com.example.Backendmed21.entities.News;
import com.example.Backendmed21.repositories.DiscourRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class DiscourService {
    @Autowired
    private DiscourRepository clt;
    public Discour AjouterDiscour(Discour f) {

        return clt.save(f);
    }

    public Discour ModifierDiscour(Discour f) {
        return clt.save(f);
    }

    public void deleteDiscour(Long id) {
        Discour Discour = getDiscourById(id);
        clt.delete(Discour);
    }

    public List<Discour> ListDiscour() {
        return clt.findAll();
    }

    public Optional<Discour> AfficherDiscour(Long id) {
        return clt.findById(id);
    }

    public Discour getDiscourById(long id) {
        Optional<Discour> Discour = clt.findById(id); // Récupère le Discour correspondante dans la base de données par son ID
        if (Discour.isPresent()) {
            return Discour.get(); // Retourne le Discour si elle existe
        } else {
            throw new NoSuchElementException("Discour introuvable pour l'ID: " + id); // Lance une exception si le Discour n'existe pas
        }
    }
    @Transactional
    public void SupprimerDiscour (Long id) {
        Discour discour = getDiscourById(id);
        clt.delete(discour);
    }


}
