package com.example.temparchive;

import com.example.temparchive.config.CityesSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CityesSettings.class)
public class TempArchiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TempArchiveApplication.class, args);
    }

}
