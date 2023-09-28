package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.weatherScrape.DTO.HighestTempDTO;
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

    public static HighestTempDTO getHighestTemp() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<HighestTempDTO> query = em.createQuery("SELECT new org.weatherScrape.DTO.HighestTempDTO(MAX(n.temperature)) FROM Night n", HighestTempDTO.class);
            return query.getSingleResult();
        }
    }

}