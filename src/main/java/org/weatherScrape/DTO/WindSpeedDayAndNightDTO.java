package org.weatherScrape.DTO;

import org.weatherScrape.entitiy.Forecast;
import lombok.*;

@Getter
public class WindSpeedDayAndNightDTO {

    private int getWindSpeedForDayAndNight;

    public WindSpeedDayAndNightDTO(Forecast forecast) {
        this.getWindSpeedForDayAndNight = forecast.getWindSpeedForDayAndNight();
    }


}
