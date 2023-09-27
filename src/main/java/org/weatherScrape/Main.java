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
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            JsonObject sysObject = jsonObject.getAsJsonObject("sys");
          JsonElement sunrise = sysObject.get("sunrise");
          JsonElement sunset = sysObject.get("sunset");
          JsonElement dt = jsonObject.get("dt");
            System.out.println(dt);
            System.out.println(sunrise);
            System.out.println(sunset);
            Date date = new Date(sunrise.getAsLong() * 1000L);
            Date date1 = new Date(sunset.getAsLong() * 1000L);
            Date date2 = new Date(dt.getAsLong() * 1000L);
            System.out.println("date for weather " + date2);
            System.out.println(" sunrise : " + date);
            System.out.println(" sunset : " + date1);

            CurrentWeather currentWeather = new CurrentWeather(date, date1, date2);




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}