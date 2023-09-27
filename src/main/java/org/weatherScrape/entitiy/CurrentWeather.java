package org.weatherScrape.entitiy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Getter
@NoArgsConstructor

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
    private int all;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    public LocalTime sunrise;

    @Column(nullable = false)
    public LocalTime sunset;


    @Column(nullable = false)
    public LocalDateTime dt;

    public CurrentWeather(LocalTime sunrise, LocalTime sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}
