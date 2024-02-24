package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.Prixavenir;
import com.example.Backendmed21.entities.PrixavenirResponse;
import com.example.Backendmed21.entities.Prixdecerner;
import com.example.Backendmed21.repositories.PrixdecernerRepository;
import com.example.Backendmed21.services.PrixavenirService;
import com.example.Backendmed21.services.PrixdecernerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/Prixavenir")
public class PrixavenirController {
    @Autowired
    private PrixavenirService cltS ;
    @Autowired
    private PrixdecernerRepository p ;


    @PostMapping("/ADDPrixavenir")
    public String addPrixavenir (HttpServletRequest request, @RequestParam("image") MultipartFile file, @RequestParam ("nom_prix") String T, @RequestParam ("description") String d,@RequestParam ("historique") Long historique) throws IOException, SerialException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Prixavenir Prixavenir= new Prixavenir();
        Prixavenir.setImage(blob);
        Prixavenir.setDescription(d);
        Prixavenir.setNom_prix(T);

        Prixavenir.setHistorique(p.getById(historique));

        cltS.AjouterPrixavenir(Prixavenir);
        return "redirect:/";

    }
    @PostMapping("/UPDATEPrixavenir")
    public Prixavenir UpdatePrixavenir (@Validated @RequestBody @Valid Prixavenir f) {
        return cltS.ModifierPrixavenir(f);
    }

    @DeleteMapping("/DELETEPrixavenir/{id}")
    public void DeletePrixavenir(@PathVariable Long id) {
        cltS.deletePrixavenir(id);
    }




    @GetMapping("/getAllPrixavenir")
    public ResponseEntity<List<PrixavenirResponse>> getAllPrixavenir() throws SQLException {
        List<Prixavenir> PrixavenirList = cltS.ListPrixavenir();

        if (PrixavenirList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PrixavenirResponse> PrixavenirResponses = new ArrayList<>();

        for (Prixavenir Prixavenir : PrixavenirList) {
            byte[] imageBytes = Prixavenir.getImage().getBytes(1, (int) Prixavenir.getImage().length());
            PrixavenirResponse PrixavenirResponse = new PrixavenirResponse(Prixavenir.getId(), Prixavenir.getNom_prix(), Prixavenir.getDescription(),Prixavenir.getDatepub() ,imageBytes,Prixavenir.getHistorique());
            PrixavenirResponses.add(PrixavenirResponse);
        }

        return ResponseEntity.ok().body(PrixavenirResponses);
    }



    // display image
    @GetMapping("/display")
    public ResponseEntity<PrixavenirResponse> displayImage(@RequestParam("id") long id) throws IOException, SQLException
    {

        Prixavenir Prixavenir = cltS.getPrixavenirById(id);

        if (Prixavenir == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Prixavenir.getImage().getBytes(1, (int) Prixavenir.getImage().length());

        PrixavenirResponse PrixavenirResponse = new PrixavenirResponse(Prixavenir.getId(), Prixavenir.getNom_prix(), Prixavenir.getDescription(),Prixavenir.getDatepub() ,imageBytes,Prixavenir.getHistorique());

        return ResponseEntity.ok().body(PrixavenirResponse);
    }


}
