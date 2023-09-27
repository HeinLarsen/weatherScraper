package org.weatherScrape.DTO;

import lombok.Getter;
import org.weatherScrape.entitiy.Forecast;

@Getter
public class HighestPrecipitationProbabilityDTO {

    private int highestPrecipitationProbability;

    public HighestPrecipitationProbabilityDTO(Forecast forecast) {
        this.highestPrecipitationProbability = forecast.getHighestPrecipitationProbability();
}
}
