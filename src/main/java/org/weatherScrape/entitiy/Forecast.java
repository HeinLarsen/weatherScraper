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
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private City city;

    @Column(nullable = false)
    private LocalTime sunrise;

    @Column(nullable = false)
    private LocalTime sunset;

    @OneToOne(cascade = CascadeType.ALL)
    private Day day;

    @OneToOne(cascade = CascadeType.ALL)
    private Night night;

    @Temporal(TemporalType.DATE)
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

}
