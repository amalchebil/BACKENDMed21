package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.Fichier;
import com.example.Backendmed21.entities.News;
import com.example.Backendmed21.repositories.FichierRepository;
import com.example.Backendmed21.services.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/News")
public class NewsController {
    @Autowired
    private NewsService cltS ;
    @Autowired
   private FichierRepository fichierRepository;



    @PostMapping("/ADDNews")
    public String addNews (HttpServletRequest request, @RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        News news= new News();
        news.setImage(blob);
        cltS.AjouterNews(news);
        return "redirect:/";

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





    // display image
    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") long id) throws IOException, SQLException
    {
        News image = cltS.getNewsById(id);
        byte [] imageBytes = null;
        imageBytes = image.getImage().getBytes(1,(int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }





     /* @PostMapping(value = {"/ADDfichier/{id}"}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
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

    }*/


}
