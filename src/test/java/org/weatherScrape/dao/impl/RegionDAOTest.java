package org.weatherScrape.dao.impl;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.weatherScrape.entitiy.City;
import org.weatherScrape.entitiy.Region;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegionDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static RegionDAO dao;


    @BeforeEach
    void setUpEach() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        dao = RegionDAO.getInstance(emf);
        deleteAllRegions();
        createRegions();
    }

    private static void deleteAllRegions() {
        em.getTransaction().begin();
        em.createNamedQuery("Region.deleteAllRegions").executeUpdate();
        em.getTransaction().commit();
    }

    private static void createRegions() {
        Region Jutland = new Region(1, "Jylland", "Denmark", "DK");
        Region zealand = new Region(2, "Sjælland", "Denmark", "DK");
        em.getTransaction().begin();
        em.persist(Jutland);
        em.persist(zealand);
        em.getTransaction().commit();


    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }

    @Test
    void save(){
        Region test = new Region(3, "testRegion", "testCountry", "DK");
        var savedEntity = dao.save(test);
        assertEquals(test.getId(), savedEntity.getId());
    }

    @Test
    void getById() {
        var res = dao.getById(1);
        assertEquals(1, res.getId());
    }

    @Test
    void getAll() {
        var res = dao.getAll();
        assertEquals(2, res.size());
    }

    @Test
    void saveAll() {
        Region test = new Region(3, "testRegion", "testCountry", "DK");
        Region test2 = new Region(4, "testRegion2", "testCountry2", "DK");
        //create list and add test1 and test2
       var list = new java.util.ArrayList<Region>();
         list.add(test);
            list.add(test2);
        dao.saveAll(list);

        var res = dao.getAll();
        assertEquals(4, res.size());
    }
}
