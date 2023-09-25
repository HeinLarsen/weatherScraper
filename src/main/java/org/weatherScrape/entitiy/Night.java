package org.weatherScrape.entitiy;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Night extends WeatherData {

    public Night(int temperature, int cloudCover, int windGust, int windSpeed, String windDirection, int thunderProbability, int precipitation, int precipitationProbability, String statement) {
        super(temperature, cloudCover, windGust, windSpeed, windDirection, thunderProbability, precipitation, precipitationProbability, statement);
    }
}
