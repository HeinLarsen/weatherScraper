package org.weatherScrape.DTO;

import org.weatherScrape.entitiy.Forecast;

public class HighestTempDTO {

    private Forecast forecast;

    public HighestTempDTO(Forecast forecast) {
        this.forecast = forecast;
    }

    public int getHighestTemp() {
        return forecast.getHighestTemperature();
    }
}
