package org.weatherScrape.dao.impl;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.weatherScrape.entitiy.Night;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NightDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static NightDAO dao;



    @BeforeEach
    public void setUp() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        dao = NightDAO.getInstance(emf);
        deleteAllNights();
        resetIdSequenceForNightTable();
        createNights();

    }

    private static void deleteAllNights() {
        em.getTransaction().begin();
        em.createNamedQuery("Night.deleteAllNights").executeUpdate();
        em.getTransaction().commit();
    }

    private static void resetIdSequenceForNightTable() {
        em.getTransaction().begin();
        em.createNativeQuery("TRUNCATE TABLE night RESTART IDENTITY CASCADE").executeUpdate();
        em.getTransaction().commit();

    }

    private static void createNights() {
        Night Thursday = new Night(18, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain");
        Night Friday = new Night(19, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain");
        em.getTransaction().begin();
        em.persist(Thursday);
        em.persist(Friday);
        em.getTransaction().commit();
    }

    @AfterEach
    public void tearDownAll() {

        em.close();
    }

    @Test
    void save() {
        Night test = new Night(20, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain");
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
        Night test = new Night(20, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain");
        Night test2 = new Night(20, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain");
        var list = new java.util.ArrayList<Night>();
        list.add(test);
        list.add(test2);
        dao.saveAll(list);
        var res = dao.getAll();
        assertEquals(4, res.size());
    }





}
