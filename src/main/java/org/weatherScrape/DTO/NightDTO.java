package org.weatherScrape.DTO;
import lombok.*;

@Getter
public class NightDTO {

    private int temperature;
    private int cloudCover;
    private int windGust;
    private int windSpeed;
    private String windDirection;
    private int thunderProbability;
    private int precipitation;
    private int precipitationProbability;
    private String statement;


    public NightDTO(int temperature, int cloudCover, int windGust, int windSpeed, String windDirection, int thunderProbability, int precipitation, int precipitationProbability, String statement) {
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
