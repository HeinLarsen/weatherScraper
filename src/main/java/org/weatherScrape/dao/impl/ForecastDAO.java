package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.entitiy.Forecast;

public class ForecastDAO extends GenericDAO<Forecast> {

    private static ForecastDAO instance;

    private ForecastDAO(EntityManagerFactory emf) {
        super(Forecast.class);
        super.setEntityManagerFactory(emf);
    }

    public static ForecastDAO getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new ForecastDAO(emf);
        }
        return instance;
    }
}
