package com.techelevator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Landmark;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class GooglePlacesService {

    private String apiUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=Philadelphia&key=";
    private String key = "AIzaSyAIPv8AuAwBzetUcUQgpfkpeIVwzFbBpWo";
    private String params =;

    public List<Landmark> getApiResults(){
        List<Landmark> results = new ArrayList<>();

        String url = this.apiUrl + this.key + this.params;

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;



    return results;
    }
}
