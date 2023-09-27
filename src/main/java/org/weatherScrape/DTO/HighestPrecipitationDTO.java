package org.weatherScrape.DTO;

import org.weatherScrape.entitiy.Forecast;

public class HighestPrecipitationDTO {

    private Forecast forecast;





    public HighestPrecipitationDTO(Forecast forecast) {
        this.forecast = forecast;
    }

    public double getHighestPrecipitation() {
        return forecast.getHighestPrecipitation();
    }



}
