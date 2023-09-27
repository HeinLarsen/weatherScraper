package org.weatherScrape.DTO;
import lombok.*;
import org.weatherScrape.entitiy.Forecast;

@Getter
public class ThunderPropDTO {

    public Forecast forecast;

    public ThunderPropDTO(Forecast forecast) {
        this.forecast = forecast;
    }


}
