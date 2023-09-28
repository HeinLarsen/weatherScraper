package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.weatherScrape.DTO.HighestTempDTO;
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

    public static HighestTempDTO getHighestTemp() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<HighestTempDTO> query = em.createQuery("SELECT new org.weatherScrape.DTO.HighestTempDTO(MAX(d.temperature)) FROM Day d", HighestTempDTO.class);
            return query.getSingleResult();
        }
    }

}
