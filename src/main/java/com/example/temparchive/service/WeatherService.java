package com.example.temparchive.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    @Value("${openweathermapkey}")
    private String apiKeyOpenWeatherMap;

    @Value("${openweatherapi}")
    private String apiKeyOpenWeatherApi;

    public WeatherService(){
        restTemplate = new RestTemplate();
    }

    @Async
    public CompletableFuture<Double> getTemperaturOpenWeatherMap(String city){
        Double result = 0d;
        try {
            LinkedHashMap jsonData = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKeyOpenWeatherMap, LinkedHashMap.class);
            //Get temp and convert Kelvin to Celsius
            result = (((Double) ((LinkedHashMap) jsonData.get("main")).get("temp")) - 273.15d);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<Double> getTemperaturOpenMeteo(String city){
        Double result = 0d;
        try {
            LinkedHashMap cityCoordinate = (LinkedHashMap) restTemplate.getForObject("https://geocoding-api.open-meteo.com/v1/search?name=" + city.split(",")[0] + "&count=1&format=json", LinkedHashMap.class);
            ArrayList coordinate = (ArrayList) cityCoordinate.get("results");
            LinkedHashMap coord = new LinkedHashMap<>((Map) coordinate.get(0));
            LinkedHashMap weather = restTemplate.getForObject("https://api.open-meteo.com/v1/forecast?latitude=" + coord.get("latitude") + "&longitude=" + coord.get("longitude") + "&current=temperature_2m", LinkedHashMap.class);
            result = (Double) ((LinkedHashMap) weather.get("current")).get("temperature_2m");
        }catch (Exception e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<Double> getTemperaturWeatherApi(String city){
        Double result = 0d;
        try {
            LinkedHashMap jsonData = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=" + apiKeyOpenWeatherApi + "&q=" + city, LinkedHashMap.class);
            result = (Double) ((LinkedHashMap) (jsonData.get("current"))).get("temp_c");
        }catch (Exception e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(result);
    };
}
