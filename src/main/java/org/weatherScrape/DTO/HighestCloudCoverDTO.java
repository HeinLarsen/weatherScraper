package org.weatherScrape.DTO;

import lombok.Getter;
import org.weatherScrape.entitiy.Forecast;

@Getter
public class HighestCloudCoverDTO {

    private int highestCloudCover;

    public HighestCloudCoverDTO(Forecast forecast) {
        this.highestCloudCover = forecast.getHighestCloudCover();
    }


}
