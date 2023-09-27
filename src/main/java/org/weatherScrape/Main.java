package org.weatherScrape;

import jakarta.persistence.EntityManagerFactory;
import org.jsoup.nodes.Document;
import org.weatherScrape.config.HibernateConfig;
import org.weatherScrape.util.Scraper;
import org.weatherScrape.util.WeatherApiClient;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


     /*   Document doc = Scraper.fetchData("https://www.accuweather.com/en/browse-locations/eur/dk");

        var regions = Scraper.getRegions(doc);

        System.out.println(regions);

        var cities = Scraper.getCities(regions, "https://www.accuweather.com/en/browse-locations/eur/");

        var forecasts = Scraper.getForecasts(cities, "https://www.accuweather.com/en/");

        forecasts.forEach(System.out::println);*/

        try {
            WeatherApiClient.getWeatherDataNow();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}