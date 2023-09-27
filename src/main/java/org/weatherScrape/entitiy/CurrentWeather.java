package org.weatherScrape.entitiy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Entity
@Getter
@NoArgsConstructor
@ToString

public class CurrentWeather {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double temp;

    @Column(nullable = false)
    private double feels_like;

    @Column(nullable = false)
    private int humidity;

    @Column(nullable = false)
    private int cloudCover;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDate sunrise;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDate sunset;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDate forecastDate;

    public CurrentWeather(LocalDate sunrise, LocalDate sunset, LocalDate forecastDate) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.forecastDate = forecastDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSunrise(LocalDate sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(LocalDate sunset) {
        this.sunset = sunset;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }
}
