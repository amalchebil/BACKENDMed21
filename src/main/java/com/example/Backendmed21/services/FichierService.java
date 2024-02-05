package com.example.Backendmed21.services;

import com.example.Backendmed21.entities.Fichier;
import com.example.Backendmed21.repositories.FichierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichierService {
    @Autowired
    private FichierRepository gar ;


    @Transactional
    public Fichier AjouterFichier (Fichier f){

        return gar.save(f);
    }

    @Transactional
    public Fichier ModifierFichier (Fichier f){
        return gar.save(f);
    }

    @Transactional
    public void SupprimerFichier (Long id) {
        Fichier f = gar.getById(id);
        gar.delete(f);
    }

    @Transactional
    public List<Fichier> ListFichier(){
        return gar.findAll();
    }

    @Transactional
    public Optional<Fichier> AfficherFichier(Long  id) {
        return gar.findById(id);
    }
}
