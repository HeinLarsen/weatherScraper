package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Forecast.deleteAllForecasts", query = "DELETE FROM Forecast f")
})
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private City city;

    @Column(nullable = false)
    private LocalTime sunrise;

    @Column(nullable = false)
    private LocalTime sunset;

    @OneToOne(cascade = CascadeType.ALL)
    private Day day;

    @OneToOne(cascade = CascadeType.ALL)
    private Night night;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    public Forecast(LocalTime sunrise, LocalTime sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setNight(Night night) {
        this.night = night;
    }

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
    }


    public double getHighestPrecipitation() {
        return Math.max(day.getPrecipitation(), night.getPrecipitation());
    }

    public int getHighestTemperature() {
        return Math.max(day.getTemperature(), night.getTemperature());
    }

    public int getHighestCloudCover() {
        return Math.max(day.getCloudCover(), night.getCloudCover());
    }

    public int getWindSpeedForDayAndNight() {
        return day.getWindSpeed() + night.getWindSpeed();
    }

    public int getThunderProbabilityForDayAndNight() {
        return day.getThunderProbability() + night.getThunderProbability();
    }

    public int getHighestPrecipitationProbability() {
        return Math.max(day.getPrecipitationProbability(), night.getPrecipitationProbability());
    }

}
