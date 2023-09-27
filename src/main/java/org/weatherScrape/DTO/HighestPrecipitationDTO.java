package org.weatherScrape.DTO;

import lombok.Getter;
import org.weatherScrape.entitiy.Forecast;

@Getter
public class HighestPrecipitationDTO {

    private double highestPrecipitation;



    public HighestPrecipitationDTO(Forecast forecast) {
        this.highestPrecipitation = forecast.getHighestPrecipitation();
    }




}
