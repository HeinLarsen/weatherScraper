package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.entitiy.Day;

public class DayDAO extends GenericDAO<Day>{

    private static DayDAO instance;

    private DayDAO(EntityManagerFactory emf) {
        super(Day.class);
        super.setEntityManagerFactory(emf);
    }

    public static DayDAO getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new DayDAO(emf);
        }
        return instance;
    }

}
