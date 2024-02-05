package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.Fichier;
import com.example.Backendmed21.services.FichierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/Fichier")
public class FichierController {
    @Autowired
    private FichierService cltS ;


    @PostMapping("/ADDFichier")

    public Fichier addFichier (@Validated @RequestBody @Valid Fichier f) {
        return cltS.AjouterFichier(f);
    }

    @PostMapping("/UPDATEFichier")

    public Fichier UpdateFichier (@Validated @RequestBody @Valid Fichier f) {
        return cltS.ModifierFichier(f);
    }

    @DeleteMapping("/DELETEFichier/{id}")

    public void DeleteFichier(@PathVariable Long id) {
        cltS.SupprimerFichier(id);
    }

    @GetMapping("/GETALLFichier")

    public List<Fichier> GETALLFichier(){
        return cltS.ListFichier();
    }

    @GetMapping("/GETFichierBYID/{id}")

    public Optional<Fichier> GetFichierById(@PathVariable Long id){
        return cltS.AfficherFichier(id);
    }
}
