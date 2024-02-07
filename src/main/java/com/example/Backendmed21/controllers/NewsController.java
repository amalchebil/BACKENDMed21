package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.Fichier;
import com.example.Backendmed21.entities.News;
import com.example.Backendmed21.repositories.FichierRepository;
import com.example.Backendmed21.services.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/News")
public class NewsController {
    @Autowired
    private NewsService cltS ;
    @Autowired
   private FichierRepository fichierRepository;

    @PostMapping(value = {"/ADDfichier/{id}"}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addFichier (@RequestPart("fichier") MultipartFile file, @PathVariable Long id) {

        News f= cltS.getNewsById(id);
        try{

            Fichier fichier= new Fichier(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes(), f);

            fichierRepository.save(fichier);
            return "fichier ajouter";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @PostMapping("/ADDNews")
    public News addNews (@Validated @RequestBody @Valid News f) {




        return cltS.AjouterNews(f);
    }





    @PostMapping("/UPDATENews")
    public News UpdateNews (@Validated @RequestBody @Valid News f) {
        return cltS.ModifierNews(f);
    }

    @DeleteMapping("/DELETENews/{id}")
    public void DeleteNews(@PathVariable Long id) {
        cltS.deleteNews(id);
    }

    @GetMapping("/GETALLNews")
    public List<News> GETALLNews(){
        return cltS.ListNews();
    }

    @GetMapping("/GETNewsBYID/{id}")
    public News GetNewsById(@PathVariable Long id){
        return cltS.getNewsById(id);
    }


}
