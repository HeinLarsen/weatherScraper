package org.weatherScrape.dao.impl;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.weatherScrape.entitiy.City;
import org.weatherScrape.entitiy.Day;
import org.weatherScrape.entitiy.Forecast;
import org.weatherScrape.entitiy.Night;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForecastDaoTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static ForecastDAO dao;
    private Day thursday = new Day(18, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain", 3);
    private Day friday = new Day(19, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain", 3);
    private Night thursdayNight = new Night(18, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain");
    private Night fridayNight = new Night(19, 98, 50, 10, "s", 0, 10.0, 10, "mostly clouded with chance of rain");
    private City copenhagen = new City(1, "København");
    private City hillerod = new City(2, "Hillerød");

    @BeforeEach
    void setUpEach() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        dao = ForecastDAO.getInstance(emf);
        deleteAllForecasts();
        resetIdSequenceForForecastTable();
        deleteAllCities();
        resetIdSequenceForCityTable();
        deleteAllDays();
        resetIdSequenceForDayTable();
        deleteAllNights();
        resetIdSequenceForNightTable();




    }

    private void createForecasts() {
        Forecast forecast1 = new Forecast(LocalTime.of(7, 13, 00), LocalTime.of(18, 53,00));
        forecast1.setCity(em.find(City.class, 1));
        forecast1.setDay(em.find(Day.class, 1));
        forecast1.setNight(em.find(Night.class, 1));
        Forecast forecast2 = new Forecast(LocalTime.of(9, 13, 00), LocalTime.of(15, 53,00));
        forecast2.setCity(em.find(City.class, 2));
        forecast2.setDay(em.find(Day.class, 2));
        forecast2.setNight(em.find(Night.class, 2));
        em.getTransaction().begin();
        em.persist(forecast1);
        em.persist(forecast2);
        em.getTransaction().commit();
    }

    private void deleteAllForecasts() {
        em.getTransaction().begin();
        em.createNamedQuery("Forecast.deleteAllForecasts").executeUpdate();
        em.getTransaction().commit();

    }

    private void deleteAllCities() {
        em.getTransaction().begin();
        em.createNamedQuery("City.deleteAllCities").executeUpdate();
        em.getTransaction().commit();
    }

    private void deleteAllDays() {
        em.getTransaction().begin();
        em.createNamedQuery("Day.deleteAllDays").executeUpdate();
        em.getTransaction().commit();
    }

    private void deleteAllNights() {
        em.getTransaction().begin();
        em.createNamedQuery("Night.deleteAllNights").executeUpdate();
        em.getTransaction().commit();
    }

    private void resetIdSequenceForCityTable() {
        em.getTransaction().begin();
        em.createNativeQuery("TRUNCATE TABLE city RESTART IDENTITY CASCADE").executeUpdate();
        em.getTransaction().commit();
    }

    private void resetIdSequenceForDayTable() {
        em.getTransaction().begin();
        em.createNativeQuery("TRUNCATE TABLE day RESTART IDENTITY CASCADE").executeUpdate();
        em.getTransaction().commit();
    }

    private void resetIdSequenceForNightTable() {
        em.getTransaction().begin();
        em.createNativeQuery("TRUNCATE TABLE night RESTART IDENTITY CASCADE").executeUpdate();
        em.getTransaction().commit();
    }

    private void resetIdSequenceForForecastTable() {
        em.getTransaction().begin();
        em.createNativeQuery("TRUNCATE TABLE forecast RESTART IDENTITY CASCADE").executeUpdate();
        em.getTransaction().commit();

    }



    @AfterEach
    void tearDownAll() {
        em.close();
    }

    @Test
    void save(){
        Forecast test = new Forecast(LocalTime.of(9, 13, 00), LocalTime.of(15, 53,00));
        test.setCity(copenhagen);
        test.setDay(thursday);
        test.setNight(thursdayNight);
        var savedEntity = dao.save(test);
        assertEquals(test.getId(), savedEntity.getId());



    }

    @Test
    void getById() {
        createForecasts();
        var res = dao.getById(1);
        assertEquals(1, res.getId());
        System.out.println(res.getId());
    }

    @Test
    void getAll() {
        createForecasts();
        var res = dao.getAll();
        assertEquals(2, res.size());
    }

    @Test
    void saveAll(){
        Forecast test = new Forecast(LocalTime.of(9, 13, 00), LocalTime.of(15, 53,00));
        test.setCity(copenhagen);
        test.setDay(thursday);
        test.setNight(thursdayNight);
        Forecast test2 = new Forecast(LocalTime.of(9, 13, 00), LocalTime.of(15, 53,00));
        test2.setCity(hillerod);
        test2.setDay(friday);
        test2.setNight(fridayNight);


       var list = new java.util.ArrayList<Forecast>();
         list.add(test);
            list.add(test2);
            dao.saveAll(list);
            var res = dao.getAll();
            assertEquals(2, res.size());
    }


}
