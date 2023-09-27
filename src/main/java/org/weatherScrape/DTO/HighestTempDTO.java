package org.weatherScrape.DTO;

import lombok.Getter;
import org.weatherScrape.entitiy.Forecast;

@Getter
public class HighestTempDTO {

    private int highestTemp;

    public HighestTempDTO(Forecast forecast) {
        this.highestTemp = forecast.getHighestTemperature();
    }


}
