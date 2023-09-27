package org.weatherScrape.dao.impl;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.weatherScrape.entitiy.Day;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static DayDAO dao;


    @BeforeEach
    public void setUp() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        dao = DayDAO.getInstance(emf);
        deleteAllDays();
        resetIdSequenceForDayTable();
        createDays();

    }

    private static void deleteAllDays() {
        em.getTransaction().begin();
        em.createNamedQuery("Day.deleteAllDays").executeUpdate();
        em.getTransaction().commit();
    }

    private static void resetIdSequenceForDayTable() {
        em.getTransaction().begin();
        em.createNativeQuery("TRUNCATE TABLE day RESTART IDENTITY CASCADE").executeUpdate();
        em.getTransaction().commit();

    }

    private static void createDays() {
        Day Thursday = new Day(18, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain", 3);
        Day Friday = new Day(19, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain", 3);
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
        Day test = new Day(20, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain", 3);
        var savedEntity = dao.save(test);
        System.out.println(test.getId());
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
        Day test1 = new Day(18, 100, 10, 10, "North", 0, 0.0, 0, "Mostly clouded", 2);
        Day test2 = new Day(10, 100, 10, 10, "southwest", 0, 0.0, 0, "freezing", 3);

        // create list and add test1 and test 2
        var list = new java.util.ArrayList<Day>();
        list.add(test1);
        list.add(test2);
        dao.saveAll(list);

        var res = dao.getAll();
        assertEquals(4, res.size());
    }


}
