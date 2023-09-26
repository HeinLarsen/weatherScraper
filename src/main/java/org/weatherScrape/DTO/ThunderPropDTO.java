package org.weatherScrape.DTO;

import org.weatherScrape.entitiy.Forecast;

public class ThunderPropDTO {

    public Forecast forecast;

    public ThunderPropDTO(Forecast forecast) {
        this.forecast = forecast;
    }

    public int getThunderProp() {
        return forecast.getThunderProbabilityForDayAndNight();
    }
}
