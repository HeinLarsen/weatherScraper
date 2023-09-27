package org.weatherScrape.entitiy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


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
    private Date sunrise;

    @Column(nullable = false)
    private Date sunset;


    @Column(nullable = false)
    private Date dt;

    public CurrentWeather(Date sunrise, Date sunset, Date dt) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.dt = dt;
    }
}
