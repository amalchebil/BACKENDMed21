package com.example.Backendmed21.services;

import com.example.Backendmed21.entities.News;
import com.example.Backendmed21.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository clt;
    public News AjouterNews(News f) {

        return clt.save(f);
    }

    public News ModifierNews(News f) {
        return clt.save(f);
    }

    public void deleteNews(Long id) {
        News News = getNewsById(id);
        clt.delete(News);
    }

    public List<News> ListNews() {
        return clt.findAll();
    }

    public Optional<News> AfficherNews(Long id) {
        return clt.findById(id);
    }

    public News getNewsById(long id) {
        Optional<News> News = clt.findById(id); // Récupère le News correspondante dans la base de données par son ID
        if (News.isPresent()) {
            return News.get(); // Retourne le News si elle existe
        } else {
            throw new NoSuchElementException("News introuvable pour l'ID: " + id); // Lance une exception si le News n'existe pas
        }
    }

}
