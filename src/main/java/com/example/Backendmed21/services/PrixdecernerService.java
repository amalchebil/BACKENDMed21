package com.example.Backendmed21.services;

import com.example.Backendmed21.entities.Prixdecerner;
import com.example.Backendmed21.repositories.PrixdecernerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PrixdecernerService {
    @Autowired
    private PrixdecernerRepository clt;
    public Prixdecerner AjouterPrixdecerner(Prixdecerner f) {

        return clt.save(f);
    }

    public Prixdecerner ModifierPrixdecerner(Prixdecerner f) {
        return clt.save(f);
    }

    public void deletePrixdecerner(Long id) {
        Prixdecerner Prixdecerner = getPrixdecernerById(id);
        clt.delete(Prixdecerner);
    }

    public List<Prixdecerner> ListPrixdecerner() {
        return clt.findAll();
    }

    public Optional<Prixdecerner> AfficherPrixdecerner(Long id) {
        return clt.findById(id);
    }

    public Prixdecerner getPrixdecernerById(long id) {
        Optional<Prixdecerner> Prixdecerner = clt.findById(id); // Récupère le Prixdecerner correspondante dans la base de données par son ID
        if (Prixdecerner.isPresent()) {
            return Prixdecerner.get(); // Retourne le Prixdecerner si elle existe
        } else {
            throw new NoSuchElementException("Prixdecerner introuvable pour l'ID: " + id); // Lance une exception si le Prixdecerner n'existe pas
        }
    }
}
