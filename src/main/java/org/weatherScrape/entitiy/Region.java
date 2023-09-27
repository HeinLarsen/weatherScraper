package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Region.deleteAllRegions", query = "DELETE FROM Region r")
})
public class Region {

    @Setter(AccessLevel.NONE)
    @Id
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false, length = 2)
    private String countryCode;

    @OneToMany(mappedBy = "region")
    @ToString.Exclude
    private Set<City> cities = new HashSet<>();

    public Region(int id, String name, String country, String countryCode) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.countryCode = countryCode;
    }

    public void addCity(City city) {
        cities.add(city);
        city.setRegion(this);
    }


}
