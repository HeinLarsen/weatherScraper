package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    private City city;

    @Column(nullable = false)
    private LocalDateTime sunrise;

    @Column(nullable = false)
    private LocalDateTime sunset;

    @OneToOne(cascade = CascadeType.ALL)
    private Day day;

    @OneToOne(cascade = CascadeType.ALL)
    private Night night;

    public Forecast(LocalDateTime date) {
        this.date = date;
    }
}
