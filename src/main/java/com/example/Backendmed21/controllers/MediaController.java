package com.example.Backendmed21.controllers;

import com.example.Backendmed21.entities.Media;
import com.example.Backendmed21.entities.MediaResponse;
import com.example.Backendmed21.services.MediaService;
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

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/Media")
public class MediaController {
    @Autowired
    private MediaService cltS ;



    @PostMapping("/ADDMedia")
    public String addMedia (HttpServletRequest request, @RequestParam("image") MultipartFile file, @RequestParam ("legende") String T) throws IOException, SerialException, SQLException {

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Media Media= new Media();
        Media.setVideo(blob);
        Media.setLegende(T);
        cltS.AjouterMedia(Media);
        return "redirect:/";

    }
    @PostMapping("/UPDATEMedia")
    public Media UpdateMedia (@Validated @RequestBody @Valid Media f) {
        return cltS.ModifierMedia(f);
    }

    @DeleteMapping("/DELETEMedia/{id}")
    public void DeleteMedia(@PathVariable Long id) {
        cltS.deleteMedia(id);
    }




    @GetMapping("/getAllMedia")
    public ResponseEntity<List<MediaResponse>> getAllMedia() throws SQLException {
        List<Media> MediaList = cltS.ListMedia();

        if (MediaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<MediaResponse> MediaResponses = new ArrayList<>();

        for (Media Media : MediaList) {
            byte[] videoBytes = Media.getVideo().getBytes(1, (int) Media.getVideo().length());
            MediaResponse MediaResponse = new MediaResponse(Media.getId(), Media.getLegende(),Media.getDate_video(),videoBytes);
            MediaResponses.add(MediaResponse);
        }

        return ResponseEntity.ok().body(MediaResponses);
    }



    // display image
    @GetMapping("/display")
    public ResponseEntity<MediaResponse> displayImage(@RequestParam("id") long id) throws IOException, SQLException
    {

        Media Media = cltS.getMediaById(id);

        if (Media == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Media.getVideo().getBytes(1, (int) Media.getVideo().length());

        MediaResponse MediaResponse = new MediaResponse(Media.getId(), Media.getLegende(), Media.getDate_video(), imageBytes);

        return ResponseEntity.ok().body(MediaResponse);
    }




}
