package org.weatherScrape;

import jakarta.persistence.EntityManagerFactory;
import org.jsoup.nodes.Document;
import org.weatherScrape.config.HibernateConfig;
import org.weatherScrape.dao.impl.CityDAO;
import org.weatherScrape.dao.impl.ForecastDAO;
import org.weatherScrape.dao.impl.GenericDAO;
import org.weatherScrape.dao.impl.RegionDAO;
import org.weatherScrape.entitiy.*;
import org.weatherScrape.util.Scraper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        ForecastDAO forecastDAO = ForecastDAO.getInstance(emf);




        var forecasts = scrape();


        forecastDAO.saveAll(forecasts);

        forecastDAO.getById(1);



    }

    private static List<Forecast> scrape() {
        Document doc = Scraper.fetchData("https://www.accuweather.com/en/browse-locations/eur/dk");

        var regions = Scraper.getRegions(doc);

        var cities = Scraper.getCities(regions, "https://www.accuweather.com/en/browse-locations/eur/");

        var forecasts = Scraper.getForecasts(cities, "https://www.accuweather.com/en/");

        return forecasts;
    }
}