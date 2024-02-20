package com.example.Backendmed21.services;

import com.example.Backendmed21.entities.Prixavenir;
import com.example.Backendmed21.repositories.PrixavenirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PrixavenirService {
    @Autowired
    private PrixavenirRepository clt;
    public Prixavenir AjouterPrixavenir(Prixavenir f) {
        f.setDatepub(LocalDate.now());
        return clt.save(f);
    }

    public Prixavenir ModifierPrixavenir(Prixavenir f) {
        return clt.save(f);
    }

    public void deletePrixavenir(Long id) {
        Prixavenir Prixavenir = getPrixavenirById(id);
        clt.delete(Prixavenir);
    }

    public List<Prixavenir> ListPrixavenir() {
        return clt.findAll();
    }

    public Optional<Prixavenir> AfficherPrixavenir(Long id) {
        return clt.findById(id);
    }

    public Prixavenir getPrixavenirById(long id) {
        Optional<Prixavenir> Prixavenir = clt.findById(id); // Récupère le Prixavenir correspondante dans la base de données par son ID
        if (Prixavenir.isPresent()) {
            return Prixavenir.get(); // Retourne le Prixavenir si elle existe
        } else {
            throw new NoSuchElementException("Prixavenir introuvable pour l'ID: " + id); // Lance une exception si le Prixavenir n'existe pas
        }
    }

}
