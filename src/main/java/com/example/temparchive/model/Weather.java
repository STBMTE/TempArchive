package com.example.temparchive.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private Double temperature;
    private Timestamp date;

    public Weather(String city, Double temperature, Timestamp measurementTime){
        this.city = city;
        this.temperature = temperature;
        this.date = measurementTime;
    }

    public Weather(){
    }

    public Timestamp getDate() {
        return date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
