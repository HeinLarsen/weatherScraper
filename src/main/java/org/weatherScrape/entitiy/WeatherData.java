package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@NoArgsConstructor
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int temperature;

    @Column(nullable = false)
    private int cloudCover;

    @Column(nullable = false)
    private int windGust;

    @Column(nullable = false)
    private int windSpeed;

    @Column(nullable = false)
    private String windDirection;

    @Column(nullable = false)
    private int thunderProbability;

    @Column(nullable = false)
    private double precipitation;

    @Column(nullable = false)
    private int precipitationProbability;

    @Column(nullable = false)
    private String statement;

    public WeatherData(int temperature, int cloudCover, int windGust, int windSpeed, String windDirection, int thunderProbability, double precipitation, int precipitationProbability, String statement) {
        this.temperature = temperature;
        this.cloudCover = cloudCover;
        this.windGust = windGust;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.thunderProbability = thunderProbability;
        this.precipitation = precipitation;
        this.precipitationProbability = precipitationProbability;
        this.statement = statement;
    }

}
