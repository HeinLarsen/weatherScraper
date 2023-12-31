package org.weatherScrape.entitiy;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "City.deleteAllCities", query = "DELETE FROM City c")
})
public class City {

    @Setter(AccessLevel.NONE)
    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Region region;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
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
