package com.example.temparchive.service;

import com.example.temparchive.model.Weather;
import com.example.temparchive.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class WeatherArchiveService {
    @Autowired
    private WeatherRepository weatherRepository;

    public Optional<List<Weather>> getWeather(String city, Optional<Timestamp> date){
        if(date.isPresent()) {
            weatherRepository.findAllByDateAndCity(date.get(), city);
        }
        AtomicReference<Weather> lastWeather = new AtomicReference<>(new Weather("", 0d, new Timestamp(0)));
        List<Weather> weathers = weatherRepository.findAllByCity(city);
        weathers.forEach(weather -> {
            if(weather.getDate().after(lastWeather.get().getDate())){
                lastWeather.set(weather);
            }
        });
        return Optional.of(Collections.singletonList(lastWeather.get()));
    }

    public Iterable<Weather> getAll(){
        return weatherRepository.findAll();
    }
}
