package org.weatherScrape;

import jakarta.persistence.EntityManagerFactory;
import org.jsoup.nodes.Document;
import org.weatherScrape.config.HibernateConfig;
import org.weatherScrape.dao.impl.CityDAO;
import org.weatherScrape.dao.impl.GenericDAO;
import org.weatherScrape.dao.impl.RegionDAO;
import org.weatherScrape.entitiy.City;
import org.weatherScrape.entitiy.Region;
import org.weatherScrape.util.Scraper;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


//        scrape();
        RegionDAO regionDAO = RegionDAO.getInstance(emf);
        CityDAO cityDAO = CityDAO.getInstance(emf);


        Region region = new Region(1, "Malmo", "Sweden", "SE");
        City city = new City(1, "Malmo");
        city.setRegion(region);
        cityDAO.save(city);


        regionDAO.getByCountryISO("SE").forEach(System.out::println);




    }

    private static void scrape() {
        Document doc = Scraper.fetchData("https://www.accuweather.com/en/browse-locations/eur/dk");

        var regions = Scraper.getRegions(doc);

        System.out.println(regions);

        var cities = Scraper.getCities(regions, "https://www.accuweather.com/en/browse-locations/eur/");

        var forecasts = Scraper.getForecasts(cities, "https://www.accuweather.com/en/");

        forecasts.forEach(System.out::println);
        System.out.println(forecasts.size());
    }
}