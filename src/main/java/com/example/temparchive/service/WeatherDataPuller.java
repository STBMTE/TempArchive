package com.example.temparchive.service;

import com.example.temparchive.config.CityesSettings;
import com.example.temparchive.model.Weather;
import com.example.temparchive.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class WeatherDataPuller {
    @Autowired
    private CityesSettings cityesSettings;

    private WeatherService weatherService;

    public WeatherDataPuller(WeatherService weatherService){
        this.weatherService = weatherService;
    }
    @Autowired
    private WeatherRepository weatherRepository;

    @Scheduled(fixedDelayString = "${weatherdelay}")
    public void RequestWeathers(){
        if(cityesSettings == null){
            System.out.println("Empty");
            return;
        }
        if(cityesSettings.isEmpty()){
            System.out.println("NULL");
            return;
        }
        Hashtable <String, Double> temperature = new Hashtable<>();

        cityesSettings.getCitys().forEach(city -> {
            Double openWeatherMapTemperatureFuture = weatherService.getTemperaturOpenWeatherMap(city);
            Double openMeteoTemperatureFuture = weatherService.getTemperaturOpenMeteo(city);
            Double openWeatherApiFuture = weatherService.getTemperaturWeatherApi(city);
            temperature.put(city, (openWeatherMapTemperatureFuture + openMeteoTemperatureFuture + openWeatherApiFuture) / 3);
        });

        temperature.forEach((city, weather) -> {
            weatherRepository.save(new Weather(city, weather, Timestamp.valueOf(LocalDateTime.now())));
        });
    }
}
