package com.demo.librarychallenge.models.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private static List<Film> films;
    private static Library instance;

    public static Library getLibrary(){
        if(instance == null){
            instance = new Library(new ArrayList<>());
            return instance;
        }else{
            return Library.getInstance();
        }
    }

    private Library(List<Film> films) {
        this.films = films;
    }

    private static Library getInstance(){
        return instance;
    }

    public List<Film> getFilms() {
        return films;
    }
}
