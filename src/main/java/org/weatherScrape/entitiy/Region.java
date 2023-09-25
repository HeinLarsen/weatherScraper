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
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "region")
    private Set<City> cities = new HashSet<>();

    public Region(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void addCity(City city) {
        cities.add(city);
        city.setRegion(this);
    }


}
