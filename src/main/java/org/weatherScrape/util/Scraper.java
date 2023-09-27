package org.weatherScrape.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.weatherScrape.entitiy.*;
import org.weatherScrape.helper.Helpers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class Scraper {
    public static Document fetchData(String _url) {
        Document doc = null;
        try {
        doc = Jsoup.connect(_url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")
                .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static List<Region> getRegions(Document doc) {
        List<Region> regions = new ArrayList<>();
        // tmp hardcode to Capital Region of Denmark
        Elements elements = doc.select("body > div > div.two-column-page-content > div.page-column-1 > div.page-content.content-module > div.result-container > a:nth-child(1)");
        Elements country = doc.getElementsByClass("location-title");



        for (Element element : elements) {
            String name = element.text();
            String countryName = country.text();
            int id = Integer.parseInt(element.attr("href").split("/")[5]);
            String countryCode = element.attr("href").split("/")[4];
            Region region = new Region(id, name, countryName, countryCode);
            regions.add(region);
        }

        return regions;
    }

    public static List<City> getCities(List<Region> regions, String baseUrl) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(availableProcessors);
        List<City> cities = new ArrayList<>();
        AtomicLong scrapeTime = new AtomicLong();
        for (Region region : regions) {
            Future<List<City>> cityFutures = es.submit(
                    () -> {
                        long startTime = System.nanoTime();
                        System.out.println("Fetching cities for " + region.getName() + " on thread " + Thread.currentThread().getName());
                        List<City> regionCities = new ArrayList<>();
                        Document doc = fetchData(baseUrl + region.getCountryCode() + "/" + region.getId());
                        Elements aCities = doc.select("body > div > div.two-column-page-content > div.page-column-1 > div.page-content.content-module > div.result-container > a");
                        for (Element aCity : aCities) {
                            String name = aCity.text();
                            String str = aCity.attr("href");
                            int id = Integer.parseInt(str.substring(str.indexOf("key=") + 4, str.indexOf("&")));
                            City city = new City(id, name);
                            city.setRegion(region);
                            regionCities.add(city);
                        }
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime);
                        scrapeTime.addAndGet(duration);
                        System.out.println("Finished fetching cities for " + region.getName() + " on thread " + Thread.currentThread().getName() + " in " + duration/1000000 + " milliseconds");
                        return regionCities;
                    }
            );
            try {
                cities.addAll(cityFutures.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
        System.out.println("Total scrape time: " + scrapeTime.get()/1000000 + " milliseconds");
        // Might be needed because there's two Ågerup on the website?
        // cities.removeIf(city -> cities.stream().anyMatch(c -> c.getName().equals(city.getName()) && c.getId() != city.getId()));
        return cities;
    }

    public static List<Forecast> getForecasts(List<City> cities, String baseUrl) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(availableProcessors);
        
        List<Forecast> forecasts = new ArrayList<>();
        AtomicLong scrapeTime = new AtomicLong();
        for (City city : cities) {
            Future<List<Forecast>> forecastFutures = es.submit(
                    () -> {
                        long startTime = System.nanoTime();

                        System.out.println("Fetching forecasts for " + city.getName() + " on thread " + Thread.currentThread().getName());

                        List<Forecast> cityForecasts = new ArrayList<>();
                        Document doc = fetchData(baseUrl + city.getRegion().getCountryCode() + "/" + city.getName() + "/" + city.getId() + "/current-weather/" + city.getId());

                        // sunrise
                        Element sunriseAndSunset = doc.getElementsByClass("sunrise-sunset").first();
                        String sunriseStr = sunriseAndSunset.select("div > div.panel.right > div:nth-child(2) > span.text-value").text();
                        LocalTime sunrise = Helpers.getLocalTime(sunriseStr);

                        // sunset
                        String sunsetStr = sunriseAndSunset.select("div > div.panel.left > div:nth-child(2) > span.text-value").text();
                        LocalTime sunset = Helpers.getLocalTime(sunsetStr);
                        Forecast forecast = new Forecast(sunrise, sunset);
                        Elements aForecasts = doc.getElementsByClass("half-day-card");


                        for (Element aForecast : aForecasts) {
                            Elements panels = aForecast.select("div.half-day-card-content > div.panels > div.left > .panel-item");
                            panels.addAll(aForecast.select("div.half-day-card-content > div.panels > div.right > .panel-item"));

                            String statement = aForecast.getElementsByClass("phrase").first().text();

                            String windDirection = "";
                            int windSpeed = 0;
                            int windGust = 0;
                            int thunder = 0;;
                            int precipitationInt = 0;
                            double precipitationDouble = 0;
                            int uvIndex = 0;
                            int cloudCoverInt = 0;

                            for (Element panel : panels) {
                                switch (panel.ownText()) {
                                    case "Wind":
                                        String wind = panel.select("span").text();
                                        windDirection = wind.split(" ")[0];
                                        windSpeed = Integer.parseInt(wind.split(" ")[1]);
                                        break;
                                    case "Gusts":
                                        windGust = Integer.parseInt(panel.select("span").text().split(" ")[0]);
                                        break;
                                    case "Thunderstorms":
                                        String thunderProbability = panel.select("span").text();
                                        thunder = Integer.parseInt(Helpers.charVanish(thunderProbability, "%"));
                                        break;
                                    case "Precipitation":
                                        String precipitation = panel.select("span").text();
                                        precipitationDouble = Double.parseDouble(Helpers.charVanish(precipitation, "mm"));
                                        break;
                                    case "Probability of Precipitation":
                                        String precipitationProbability = panel.select("span").text();
                                        precipitationInt = Integer.parseInt(Helpers.charVanish(precipitationProbability, "%"));
                                        break;
                                    case "Max UV Index":
                                        String uv = panel.select("span").text();
                                        uvIndex = Integer.parseInt(uv.split(" ")[0]);
                                        break;
                                    case "Cloud Cover":
                                        String cloudCover = panel.select("span").text();
                                        cloudCoverInt = Integer.parseInt(Helpers.charVanish(cloudCover, "%"));
                                        break;
                                    default:
                                        break;
                                }
                            }

                            String dayOrNight = aForecast.select("div.half-day-card-header > div.half-day-card-header__title > h2").text();

                            int temperature = Integer.parseInt(aForecast.getElementsByClass("temperature").first().text().split("°")[0]);

                            if (dayOrNight.equals("Day")) {
                                Day day = new Day(temperature, cloudCoverInt, windGust, windSpeed, windDirection, thunder, precipitationDouble, precipitationInt, statement, uvIndex);
                                forecast.setDay(day);
                            } else {
                                Night night = new Night(temperature, cloudCoverInt, windGust, windSpeed, windDirection, thunder, precipitationDouble, precipitationInt, statement);
                                forecast.setNight(night);
                            }
                            forecast.setCity(city);
                        }
                        cityForecasts.add(forecast);
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime);
                        scrapeTime.addAndGet(duration);
                        System.out.println("Finished fetching forecasts for " + city.getName() + " on thread " + Thread.currentThread().getName() + " in " + duration/1000000 + " milliseconds" );
                        return cityForecasts;
                    });
            try {
                forecasts.addAll(forecastFutures.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            es.shutdown();
            System.out.println("Total scrape time: " + scrapeTime.get()/1000000 + " milliseconds");

        return forecasts;
        }

}
