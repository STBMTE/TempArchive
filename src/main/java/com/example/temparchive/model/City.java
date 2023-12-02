package com.example.temparchive.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    @Nullable
    private String countryCode;

    public City(String cityName, String countryName){
        this.cityName = cityName;
        this.countryCode = countryName;
    }

    public String getCitiName() {
        return cityName;
    }

    public City(String CitiName){
        this.cityName = CitiName;
    }
    public City(){ }

    public String getCountryCode() {
        return countryCode;
    }
}
