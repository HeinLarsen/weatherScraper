package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class City {
// comment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    private Region region;

    @OneToMany(mappedBy = "city")
    private Set<Forecast> forecasts = new HashSet<>();

    public City(String name) {
        this.name = name;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
