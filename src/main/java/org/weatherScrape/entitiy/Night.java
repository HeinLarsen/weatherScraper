package org.weatherScrape.entitiy;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Night extends WeatherData {

    public Night(int temperature, int cloudCover, int windGust, int windSpeed, String windDirection, int thunderProbability, double precipitation, int precipitationProbability, String statement) {
        super(temperature, cloudCover, windGust, windSpeed, windDirection, thunderProbability, precipitation, precipitationProbability, statement);
    }

    @Override
    public String toString() {
        return "Night{ " + super.toString() + " }";
    }
}
