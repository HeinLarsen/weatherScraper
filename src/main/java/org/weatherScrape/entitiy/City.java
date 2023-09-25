package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class City {

    @Setter(AccessLevel.NONE)
    @Id
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @ToString.Exclude
    private Region region;

    @OneToMany(mappedBy = "city")
    @ToString.Exclude
    private Set<Forecast> forecasts = new HashSet<>();

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
