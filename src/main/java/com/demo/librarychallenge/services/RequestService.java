package com.demo.librarychallenge.services;

import com.demo.librarychallenge.models.entity.Book;
import com.demo.librarychallenge.models.entity.Film;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    private Gson gson;

    @Autowired
    public RequestService(Gson gson){
        this.gson = gson;
    };

    public List request(String queryString) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8080/api/book/get?strict=false");
        StringEntity requestEntity = new StringEntity(
                queryString,
                ContentType.APPLICATION_JSON);
        request.setEntity(requestEntity);

        try{
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return (ArrayList<Book>) gson.fromJson(EntityUtils.toString(entity), ArrayList.class);
            }else{
                return new ArrayList<>();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
