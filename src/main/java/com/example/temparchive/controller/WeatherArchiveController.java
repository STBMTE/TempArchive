package com.example.temparchive.controller;

import com.example.temparchive.model.Weather;
import com.example.temparchive.service.WeatherArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherArchiveController {
    @Autowired
    private WeatherArchiveService weatherService;

    public WeatherArchiveController(WeatherArchiveService weatherService) {
        this.weatherService = weatherService;
    }

    public WeatherArchiveController(){
    }

    @GetMapping("/{city}&{date}")
    public Optional<List<Weather>> getWeather(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<Timestamp> date, @PathVariable String city){
        return weatherService.getWeather(city, date);
    }
    @GetMapping("/all")
    public Iterable<Weather> getAll(){
        return weatherService.getAll();
    }
}
