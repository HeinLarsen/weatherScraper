package org.weatherScrape.dao.impl;


import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.dao.ICityDAO;
import org.weatherScrape.entitiy.City;

public class CityDAO extends GenericDAO<City> implements ICityDAO {
    private static CityDAO instance;

    private CityDAO(EntityManagerFactory emf) {
        super(City.class);
        super.setEntityManagerFactory(emf);
    }

    public static CityDAO getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new CityDAO(emf);
        }
        return instance;
    }

}
