package org.weatherScrape.DTO;

import org.weatherScrape.entitiy.Forecast;

public class HighestCloudCoverDTO {

    private Forecast forecast;

    public HighestCloudCoverDTO(Forecast forecast) {
        this.forecast = forecast;
    }

    public int getHighestCloudCover() {
        return forecast.getHighestCloudCover();
    }
}
