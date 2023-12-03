package com.example.temparchive;

import com.example.temparchive.model.Weather;
import com.example.temparchive.repository.WeatherRepository;
import com.example.temparchive.service.WeatherArchiveService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class WeatherArchiveServiceTest {
        @Mock
        private static WeatherRepository weatherRepository;

        @InjectMocks
        private static WeatherArchiveService weatherArchiveService;

        @Test
        public void testGetWeatherByCityWithNoDate() {
            String city = "Ufa,ru";
            List<Weather> weathers = Collections.singletonList(
                    new Weather(city, -1.0, Timestamp.valueOf(LocalDateTime.of(2023, 12, 2, 0, 0)))
            );
            when(weatherRepository.findAllByCity(city)).thenReturn(weathers);
            Optional<List<Weather>> result = weatherArchiveService.getWeather(city, Optional.empty());
            assertTrue(result.isPresent());
            assertEquals(weathers, result.get());
        }

        @Test
        public void testGetWeatherByCityWithNoResult() {
            String city = "Ufa,ru";
            when(weatherRepository.findAllByCity(city)).thenReturn(Collections.emptyList());
            Optional<List<Weather>> result = weatherArchiveService.getWeather(city, Optional.empty());
            assertFalse(result.get().isEmpty());
        }

        @Test
        public void testGetAllWeather() {
            List<Weather> allWeathers = Arrays.asList(
                    new Weather("Ufa,ru", 0.0, Timestamp.valueOf(LocalDateTime.of(2023, 11, 1, 0, 0))),
                    new Weather("Chelyabinsk,ru", 1.0, Timestamp.valueOf(LocalDateTime.of(2023, 12, 13, 0, 0)))
            );
            when(weatherRepository.findAll()).thenReturn(allWeathers);
            Iterable<Weather> result = weatherArchiveService.getAll();
            assertEquals(allWeathers, result);
        }
}
