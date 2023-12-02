package com.example.temparchive.repository;

import com.example.temparchive.model.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    List<Weather> findAllByDateAndCity(Timestamp date, String city);
    List<Weather> findAllByCity(String city);
}
