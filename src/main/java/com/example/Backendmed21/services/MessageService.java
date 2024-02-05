package com.example.Backendmed21.services;

import com.example.Backendmed21.entities.Message;
import com.example.Backendmed21.repositories.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository agr;

    @Transactional
    public Message AjouterMessage(Message f){
        return agr.save(f);
    }
    @Transactional
    public Message ModifierMessage (Message f){
        return agr.save(f);
    }

    @Transactional
    public void SupprimerMessage (Long id) {
        Message f = agr.getById(id);
        agr.delete(f);
    }

    @Transactional
    public List<Message> ListMessage(){
        return agr.findAll();
    }

    @Transactional
    public Optional<Message> AfficherMessage(Long  id) {
        return agr.findById(id);
    }
    @Transactional
    public Message getMessageById(long id) {
        Optional<Message> Message = agr.findById(id); // Récupère l'Message correspondante dans la base de données par son ID
        if (Message.isPresent()) {
            return Message.get(); // Retourne l'Message si elle existe
        } else {
            throw new NoSuchElementException("Message introuvable pour l'ID: " + id); // Lance une exception si l'Message n'existe pas
        }
    }
}
