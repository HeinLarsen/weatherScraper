package org.weatherScrape.DTO;

import org.weatherScrape.entitiy.Forecast;

public class WindSpeedDayAndNightDTO {

    private Forecast forecast;

    public WindSpeedDayAndNightDTO(Forecast forecast) {
        this.forecast = forecast;
    }

    public int getWindSpeedDay() {
        return forecast.getWindSpeedForDayAndNight();
    }
}
