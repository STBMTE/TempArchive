package com.example.temparchive.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "requested")
@Component
public class CityesSettings {
    private static List<String> citys;

    public List<String> getCitys() {
        return new ArrayList<>(citys);
    }

    public boolean isEmpty(){
        return citys.isEmpty();
    }

    public void setCitys(List<String> citys) {
        this.citys = citys;
    }

    @PostConstruct
    public void logSettings() {
        System.out.println("Requested List: " + citys);
    }
}
