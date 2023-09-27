package org.weatherScrape.DTO;

import org.weatherScrape.entitiy.Forecast;

public class HighestPrecipitationProbabilityDTO {

    private Forecast forecast;

    public HighestPrecipitationProbabilityDTO(Forecast forecast) {
        this.forecast = forecast;
    }

    public int getHighestPrecipitationProbability() {
        return forecast.getHighestPrecipitationProbability();
    }
}
