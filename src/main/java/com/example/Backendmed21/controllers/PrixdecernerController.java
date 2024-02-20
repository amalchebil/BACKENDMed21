package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.Prixdecerner;
import com.example.Backendmed21.entities.PrixdecernerResponse;
import com.example.Backendmed21.services.PrixdecernerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
import java.util.Date;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/Prixdecerner")
public class PrixdecernerController {
    @Autowired
    private PrixdecernerService cltS ;
    @PostMapping("/ADDPrixdecerner")
    public String addPrixdecerner (HttpServletRequest request, @RequestParam("image") MultipartFile file, @RequestParam ("nom") String nom, @RequestParam ("objectif") String objectif, @RequestParam ("date") Date date, @RequestParam ("lieu") String lieu, @RequestParam ("membre") List<String> membre, @RequestParam ("laureat") List<String> laureat, @RequestParam ("ceremonie") String ceremonie, @RequestParam ("liens") List<String> liens, @RequestParam ("personnage") String personnage) throws IOException, SerialException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Prixdecerner Prixdecerner= new Prixdecerner();
        Prixdecerner.setImage(blob);
        Prixdecerner.setNom_prix(nom);
        Prixdecerner.setCeremonie(ceremonie);
        Prixdecerner.setDateprix(date);
        Prixdecerner.setLaureats(laureat);
        Prixdecerner.setLiens(liens);
        Prixdecerner.setLieu(lieu);
        Prixdecerner.setMembresfondateurs(membre);
        Prixdecerner.setPersonnage(personnage);
        Prixdecerner.setOjectif(objectif);
        cltS.AjouterPrixdecerner(Prixdecerner);

        return "redirect:/";

    }
    @PostMapping("/UPDATEPrixdecerner")
    public Prixdecerner UpdatePrixdecerner (@Validated @RequestBody @Valid Prixdecerner f) {
        return cltS.ModifierPrixdecerner(f);
    }

    @DeleteMapping("/DELETEPrixdecerner/{id}")
    public void DeletePrixdecerner(@PathVariable Long id) {
        cltS.deletePrixdecerner(id);
    }


    @GetMapping("/GETPrixdecernerBYID/{id}")
    public Prixdecerner GetPrixdecernerById(@PathVariable Long id){
        return cltS.getPrixdecernerById(id);
    }

    @GetMapping("/getAllPrixdecerner")
    public ResponseEntity<List<PrixdecernerResponse>> getAllPrixdecerner() throws SQLException {
        List<Prixdecerner> PrixdecernerList = cltS.ListPrixdecerner();

        if (PrixdecernerList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PrixdecernerResponse> PrixdecernerResponses = new ArrayList<>();

        for (Prixdecerner Prixdecerner : PrixdecernerList) {
            byte[] imageBytes = Prixdecerner.getImage().getBytes(1, (int) Prixdecerner.getImage().length());
            PrixdecernerResponse PrixdecernerResponse = new PrixdecernerResponse(Prixdecerner.getId(),Prixdecerner.getNom_prix(), Prixdecerner.getOjectif(),Prixdecerner.getDateprix(), Prixdecerner.getLieu(), Prixdecerner.getMembresfondateurs(),Prixdecerner.getPersonnage(), Prixdecerner.getCeremonie(),Prixdecerner.getLaureats(),Prixdecerner.getLiens(),imageBytes);
            PrixdecernerResponses.add(PrixdecernerResponse);
        }

        return ResponseEntity.ok().body(PrixdecernerResponses);
    }



    // display image
    @GetMapping("/display")
    public ResponseEntity<PrixdecernerResponse> displayImage(@RequestParam("id") long id) throws IOException, SQLException
    {

        Prixdecerner Prixdecerner = cltS.getPrixdecernerById(id);

        if (Prixdecerner == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Prixdecerner.getImage().getBytes(1, (int) Prixdecerner.getImage().length());

        PrixdecernerResponse PrixdecernerResponse = new PrixdecernerResponse(Prixdecerner.getId(),Prixdecerner.getNom_prix(), Prixdecerner.getOjectif(),Prixdecerner.getDateprix(), Prixdecerner.getLieu(), Prixdecerner.getMembresfondateurs(),Prixdecerner.getPersonnage(), Prixdecerner.getCeremonie(),Prixdecerner.getLaureats(),Prixdecerner.getLiens(),imageBytes);

        return ResponseEntity.ok().body(PrixdecernerResponse);
    }


}
