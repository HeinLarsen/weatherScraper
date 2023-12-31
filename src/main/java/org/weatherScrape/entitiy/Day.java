package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Day.deleteAllDays", query = "DELETE FROM Day d")

})

public class Day extends WeatherData {

    private int uvIndex;

    public Day(int temperature, int cloudCover, int windGust, int windSpeed, String windDirection, int thunderProbability, double precipitation, int precipitationProbability, String statement, int uvIndex) {
        super(temperature, cloudCover, windGust, windSpeed, windDirection, thunderProbability, precipitation, precipitationProbability, statement);
        this.uvIndex = uvIndex;
    }

    @Override
    public String toString() {
        return "Day{" +
                "uvIndex=" + uvIndex + super.toString() +
                '}';
    }
}
