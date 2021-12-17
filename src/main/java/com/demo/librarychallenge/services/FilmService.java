package com.demo.librarychallenge.services;

import com.demo.librarychallenge.exceptions.InvalidRequestException;
import com.demo.librarychallenge.exceptions.NotFoundException;
import com.demo.librarychallenge.models.entity.Film;
import com.demo.librarychallenge.models.entity.Library;
import com.demo.librarychallenge.models.entity.QueryRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private Gson gson;

    @Autowired
    public FilmService(Gson gson) {
        this.gson = gson;
    }

    public Film convertToObject(String json){
        return gson.fromJson(json, Film.class);
    }

    public List convertToList(String json){
        return gson.fromJson(json, ArrayList.class);
    }

    public String convertToString(Object obj){
        return gson.toJson(obj);
    }

    public void saveToList(Film film){
        Library.getLibrary().getFilms().add(film);
    }

    public void updateFilm(Film film){
        List<Film> films = Library.getLibrary().getFilms();
        for(int i = 0; i < films.size(); i++){
            if(film.equals(films.get(i))){
                films.set(i, film);
                return;
                //update first book found
            }
        }
        throw new NotFoundException("Film not found for title.");
    }

    public void deleteBook(String json){
        QueryRequest request = gson.fromJson(json, QueryRequest.class);
        checkRequest(request);
        List<Film> films = Library.getLibrary().getFilms();
        for(int i = 0; i < films.size(); i++){
            if(films.get(i).getTitle().equalsIgnoreCase(request.getTitle())){
                films.remove(i);
                return;
                //delete first found;
            }
        }
        throw new NotFoundException("Film not found for title.");
    }

    public List<Film> getFilm(String title){

        List<Film> films = Library.getLibrary().getFilms();
        List<Film> foundFilms = new ArrayList<>();

        for(Film film : films){
            if(film.getTitle().toLowerCase().contains(title)){
                foundFilms.add(film);

            }
        }
        if(foundFilms.size() == 0){
            throw new NotFoundException("Film not found for title.");
        }
        return foundFilms;
    }

    public void checkRequest(QueryRequest req){
        if(req.getTitle() == null){
            throw new InvalidRequestException("Invalid Request");
        }
    }

    public void checkBook(Film film){
        if(film.getTitle() == null){
            throw new InvalidRequestException("Invalid Request");
        }
    }
}
