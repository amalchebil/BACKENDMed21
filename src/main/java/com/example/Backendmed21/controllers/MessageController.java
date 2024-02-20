package com.example.Backendmed21.controllers;


import com.example.Backendmed21.entities.Message;
import com.example.Backendmed21.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/Message")
public class MessageController {
    @Autowired
    private MessageService cltS ;


    @PostMapping("/AjouterMessage")
    public Message ajouterMessage(@Validated @RequestBody @Valid Message f){
        return cltS.AjouterMessage(f);
    }
    @PostMapping("/UPDATEMessage")
    public Message UpdateMessage (@Validated @RequestBody @Valid Message f) {
        return cltS.ModifierMessage(f);
    }

    @DeleteMapping("/DELETEMessage/{id}")
    public void DeleteMessage(@PathVariable Long id) {
        cltS.SupprimerMessage(id);
    }

    @GetMapping("/GETALLMessage")
    public List<Message> GETALLMessage(){
        return cltS.ListMessage();
    }

    @GetMapping("/GETMessageBYID/{id}")
    public Message GetMessageById(@PathVariable Long id){
        return cltS.getMessageById(id);
    }
}
