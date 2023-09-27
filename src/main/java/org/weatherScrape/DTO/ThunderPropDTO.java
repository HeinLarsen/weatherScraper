package org.weatherScrape.DTO;
import lombok.*;
import org.weatherScrape.entitiy.Forecast;

@Getter
public class ThunderPropDTO {

    private int thunderProp;

    public ThunderPropDTO(Forecast forecast) {
        this.thunderProp = forecast.getThunderProbabilityForDayAndNight();
    }


}
