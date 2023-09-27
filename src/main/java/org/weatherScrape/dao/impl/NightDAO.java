package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.entitiy.Night;

public class NightDAO extends GenericDAO<Night>{

    private static NightDAO instance;

    private NightDAO(EntityManagerFactory emf) {
        super(Night.class);
        super.setEntityManagerFactory(emf);
    }

    public static NightDAO getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new NightDAO(emf);
        }
        return instance;
    }

}