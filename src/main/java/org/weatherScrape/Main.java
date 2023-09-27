package org.weatherScrape;

import com.google.gson.Gson;
import jakarta.persistence.EntityManagerFactory;
import org.jsoup.nodes.Document;
import org.weatherScrape.config.HibernateConfig;
import org.weatherScrape.entitiy.CurrentWeather;
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
           Gson gson = new Gson();
           WeatherApiClient.getWeatherDateNow("Helsinge");
           CurrentWeather currentWeather = gson.fromJson(WeatherApiClient.getWeatherDateNow("Helsinge"), CurrentWeather.class);
              System.out.println(currentWeather.getName());

         } catch (Exception e) {
           e.printStackTrace();


       }


    }
}