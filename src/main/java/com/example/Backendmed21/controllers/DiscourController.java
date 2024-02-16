package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.Discour;
import com.example.Backendmed21.services.DiscourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/Discour")
public class DiscourController {
    @Autowired
    private DiscourService cltS ;


    @PostMapping("/AjouterDiscour")
    public Discour ajouterDiscour(@Validated @RequestBody @Valid Discour f){
        return cltS.AjouterDiscour(f);
    }
    @PostMapping("/UPDATEDiscour")
    public Discour UpdateDiscour (@Validated @RequestBody @Valid Discour f) {
        return cltS.ModifierDiscour(f);
    }

    @DeleteMapping("/DELETEDiscour/{id}")
    public void DeleteDiscour(@PathVariable Long id) {
        cltS.SupprimerDiscour(id);
    }

    @GetMapping("/GETALLDiscour")
    public List<Discour> GETALLDiscour(){
        return cltS.ListDiscour();
    }

    @GetMapping("/GETDiscourBYID/{id}")
    public Discour GetDiscourById(@PathVariable Long id){
        return cltS.getDiscourById(id);
    }
}
