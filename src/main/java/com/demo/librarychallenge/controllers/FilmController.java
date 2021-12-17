package com.demo.librarychallenge.controllers;


import com.demo.librarychallenge.models.entity.Film;
import com.demo.librarychallenge.services.FilmService;
import com.demo.librarychallenge.services.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    FilmService filmService;
    RequestService requestService;

    public FilmController(FilmService filmService, RequestService requestService) {
        this.filmService = filmService;
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody String jsonRequest){
        try{
            Film film = filmService.convertToObject(jsonRequest);
            filmService.checkBook(film);
            filmService.saveToList(film);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Created");
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody String jsonRequest){
        try{
            Film film = filmService.convertToObject(jsonRequest);
            filmService.checkBook(film);
            filmService.updateFilm(film);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping
    public ResponseEntity deleteBook(@RequestBody String jsonRequest){
        try{
            filmService.deleteBook(jsonRequest);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/get{title}{recursive}")
    public ResponseEntity getFilm(@RequestParam String title, @RequestParam(defaultValue = "false") String recursive){
        try{
            List<Film> films = filmService.getFilm(title);
            String response = filmService.convertToString(films);
            if(recursive.equals("false")){
                System.out.println(recursive);
                List<Film> books = requestService.request(title, filmService);
                films.addAll(books);
                response = filmService.convertToString(books);
            }
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
