package com.demo.librarychallenge;

import com.demo.librarychallenge.models.entity.Film;
import com.demo.librarychallenge.models.entity.Library;
import com.demo.librarychallenge.models.entity.QueryRequest;
import com.demo.librarychallenge.services.FilmService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class FilmServiceTest {

    @Test
    public void shouldReturnEquals(){
        Film[] films = generateBooks();

        Assertions.assertEquals(films[0], films[1]);
        Assertions.assertEquals(films[0], films[2]);
        Assertions.assertNotEquals(films[0], films[3]);
    }

    @Test
    public void testListFunctions(){
        Film[] films = generateBooks();
        FilmService service = new FilmService(new Gson());
        service.saveToList(films[0]);
        Assertions.assertEquals(1, Library.getLibrary().getFilms().size());
        Assertions.assertEquals( films[0], Library.getLibrary().getFilms().get(0));

        service.saveToList(films[2]);
        Assertions.assertEquals(2, Library.getLibrary().getFilms().size());
        Assertions.assertEquals( films[0], Library.getLibrary().getFilms().get(0));
        Assertions.assertEquals( films[2], Library.getLibrary().getFilms().get(1));
        Assertions.assertEquals( films[0].getAuthor(), Library.getLibrary().getFilms().get(0).getAuthor());
        Assertions.assertEquals( films[2].getAuthor(), Library.getLibrary().getFilms().get(1).getAuthor());

        service.updateFilm(films[4]);
        Assertions.assertEquals(2, Library.getLibrary().getFilms().size());
        Assertions.assertEquals( films[4].getAuthor(), Library.getLibrary().getFilms().get(0).getAuthor());

        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setTitle("harry potter and the philosophal stone");

        service.deleteBook(service.convertToString(queryRequest));
        Assertions.assertEquals(1, Library.getLibrary().getFilms().size());
        Assertions.assertEquals( films[2].getAuthor(), Library.getLibrary().getFilms().get(0).getAuthor());

        service.saveToList(films[1]);
        service.saveToList(films[3]);
        service.saveToList(films[5]);

        queryRequest.setTitle("harry potter");

        List<Film> filmList = service.getFilm(service.convertToString(queryRequest));
        Assertions.assertEquals(3, filmList.size());
        Assertions.assertEquals( films[2].getAuthor(), filmList.get(0).getAuthor());
        Assertions.assertEquals(films[1].getAuthor(), filmList.get(1).getAuthor());
    }

    private Film[] generateBooks() {
        return new Film[]{new Film("harry potter and the philosophal stone", "author1", "3", "4", "5", "6"),
                new Film("harry potter and the philosophal stone", "author2", "3", "4", "5", "6"),
                new Film("harry potter and the philosophal stone", "author3", "4", "5", "6", "7"),
                new Film("dune", "author4", "4", "5", "6", "7"),
                new Film("harry potter and the philosophal stone", "author5", "9", "9", "9", "9"),
                new Film("harry potter and the golden chalice", "author6", "9", "9", "9", "9"),
        };
    }
}
