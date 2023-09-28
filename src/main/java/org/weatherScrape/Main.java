package org.weatherScrape;



import jakarta.persistence.EntityManagerFactory;
import org.jsoup.nodes.Document;
import org.weatherScrape.config.HibernateConfig;
import org.weatherScrape.dao.impl.ForecastDAO;
import org.weatherScrape.entitiy.CurrentWeather;
import org.weatherScrape.entitiy.Forecast;
import org.weatherScrape.util.Scraper;
import org.weatherScrape.util.WeatherApiClient;

import java.io.IOException;
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

        System.out.println(regions);

        var cities = Scraper.getCities(regions, "https://www.accuweather.com/en/browse-locations/eur/");

        var forecasts = Scraper.getForecasts(cities, "https://www.accuweather.com/en/");

        forecasts.forEach(System.out::println);


        try {
            CurrentWeather currentWeather = WeatherApiClient.getWeatherDateNow("Copenhagen");
            System.out.println(currentWeather.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return forecasts;


    }
}