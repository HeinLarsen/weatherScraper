package org.weatherScrape.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.weatherScrape.entitiy.Forecast;

@Getter
@NoArgsConstructor
@ToString
public class HighestTempDTO {

    private int highestTemp;

    public HighestTempDTO(int highestTemp) {
        this.highestTemp = highestTemp;
    }


}
