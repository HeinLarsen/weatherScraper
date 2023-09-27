package org.weatherScrape;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManagerFactory;
import org.jsoup.nodes.Document;
import org.weatherScrape.config.HibernateConfig;
import org.weatherScrape.entitiy.CurrentWeather;
import org.weatherScrape.util.Scraper;
import org.weatherScrape.util.WeatherApiClient;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


     /*   Document doc = Scraper.fetchData("https://www.accuweather.com/en/browse-locations/eur/dk");

        var regions = Scraper.getRegions(doc);

        System.out.println(regions);

        var cities = Scraper.getCities(regions, "https://www.accuweather.com/en/browse-locations/eur/");

        var forecasts = Scraper.getForecasts(cities, "https://www.accuweather.com/en/");

        forecasts.forEach(System.out::println);*/

        Gson gson = new Gson();
        try{
            String response = WeatherApiClient.getWeatherDateNow("Copenhagen");
            List<Date> dates = WeatherApiClient.convertJSONRespToDates(response);

            System.out.println(dates);






        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}