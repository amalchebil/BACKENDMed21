package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.News;
import com.example.Backendmed21.entities.NewsResponse;
import com.example.Backendmed21.services.NewsService;
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
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/News")
public class NewsController {
    @Autowired
    private NewsService cltS ;



    @PostMapping("/ADDNews")
    public String addNews (HttpServletRequest request, @RequestParam("image") MultipartFile file,@RequestParam ("titre") String T,@RequestParam ("description") String d) throws IOException, SerialException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        News news= new News();
        news.setImage(blob);
        news.setDescription(d);
        news.setTitre(T);
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


    @GetMapping("/GETNewsBYID/{id}")
    public News GetNewsById(@PathVariable Long id){
        return cltS.getNewsById(id);
    }

    @GetMapping("/getAllNews")
    public ResponseEntity<List<NewsResponse>> getAllNews() throws SQLException {
        List<News> newsList = cltS.ListNews();

        if (newsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<NewsResponse> newsResponses = new ArrayList<>();

        for (News news : newsList) {
            byte[] imageBytes = news.getImage().getBytes(1, (int) news.getImage().length());
            NewsResponse newsResponse = new NewsResponse(news.getId(), news.getTitre(), news.getDescription(), imageBytes);
            newsResponses.add(newsResponse);
        }

        return ResponseEntity.ok().body(newsResponses);
    }



    // display image
    @GetMapping("/display")
    public ResponseEntity<NewsResponse> displayImage(@RequestParam("id") long id) throws IOException, SQLException
    {

        News news = cltS.getNewsById(id);

        if (news == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = news.getImage().getBytes(1, (int) news.getImage().length());

            NewsResponse newsResponse = new NewsResponse(news.getId(), news.getTitre(), news.getDescription(), imageBytes);

        return ResponseEntity.ok().body(newsResponse);
    }





 /*@GetMapping("/GETALLNews")
    public List<News> GETALLNews(){
        return cltS.ListNews();
    }*/




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
