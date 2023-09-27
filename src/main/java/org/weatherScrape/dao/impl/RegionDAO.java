package org.weatherScrape.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import org.weatherScrape.dao.IRegionDAO;
import org.weatherScrape.entitiy.Region;

import java.util.List;


public class RegionDAO extends GenericDAO<Region> implements IRegionDAO {

    private static RegionDAO instance;

    private RegionDAO(EntityManagerFactory emf) {
        super(Region.class);
        super.setEntityManagerFactory(emf);
    }

    public static RegionDAO getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new RegionDAO(emf);
        }
        return instance;
    }

    @Override
    public List<Region> getByCountryISO(String iso) {
        try(var em = emf.createEntityManager()) {
            return em.createQuery("SELECT r FROM Region r WHERE r.countryCode = :iso", Region.class)
                    .setParameter("iso", iso)
                    .getResultList();
        }
    }
}
