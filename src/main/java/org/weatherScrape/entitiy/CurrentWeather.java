package org.weatherScrape.entitiy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDateTime sunrise;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDateTime sunset;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDateTime forecastDate;

    public CurrentWeather(LocalDateTime sunrise, LocalDateTime sunset, LocalDateTime forecastDate) {
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

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }


    public void setForecastDate(LocalDateTime forecastDate) {
        this.forecastDate = forecastDate;
    }
}
