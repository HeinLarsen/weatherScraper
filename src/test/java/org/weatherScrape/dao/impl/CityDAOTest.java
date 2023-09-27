package org.weatherScrape.dao.impl;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.weatherScrape.entitiy.City;

import static org.junit.jupiter.api.Assertions.*;

class CityDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static CityDAO dao;




    @BeforeEach
    void setUpEach() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        dao = CityDAO.getInstance(emf);
        deleteAllCities();
        createCities();
    }

    private static void deleteAllCities() {
        em.getTransaction().begin();
        em.createNamedQuery("City.deleteAllCities").executeUpdate();
        em.getTransaction().commit();
    }

    private static void createCities() {
        City copenhagen = new City(1, "København");
        City hillerod = new City(2, "Hillerød");
        em.getTransaction().begin();
        em.persist(copenhagen);
        em.persist(hillerod);
        em.getTransaction().commit();
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }
    @Test
    void save() {
        City test = new City(3, "testCity");
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
        City test1 = new City(4, "testCity1");
        City test2 = new City(5, "testCity2");
        // create list and add test1 and test2
        var list = new java.util.ArrayList<City>();
        list.add(test1);
        list.add(test2);
        dao.saveAll(list);

        var res = dao.getAll();
        assertEquals(4, res.size());
    }
}