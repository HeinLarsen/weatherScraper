package org.weatherScrape.util;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.weatherScrape.entitiy.CurrentWeather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class WeatherApiClient {

    static HttpClient httpClient = HttpClient.newHttpClient();





    public static CurrentWeather getWeatherDateNow(String cityName) throws IOException, InterruptedException {
        String apiKey = "a4e9445f16deb18de54bee308714ee5c";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=" + apiKey;
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonresp = response.body();
        CurrentWeather currentweather = new CurrentWeather();
        currentweather.setId(gson.fromJson(jsonresp, JsonObject.class).get("id").getAsInt());
        currentweather.setName(gson.fromJson(jsonresp, JsonObject.class).get("name").getAsString());
        currentweather.setHumidity(gson.fromJson(jsonresp, JsonObject.class).get("main").getAsJsonObject().get("humidity").getAsInt());
        currentweather.setCloudCover(gson.fromJson(jsonresp, JsonObject.class).get("clouds").getAsJsonObject().get("all").getAsInt());
        currentweather.setDescription(gson.fromJson(jsonresp, JsonObject.class).get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString());

        //handles dates for sunrise, sunset and date for the current weather
        JsonObject sysObject = gson.fromJson(jsonresp, JsonObject.class).get("sys").getAsJsonObject();
        JsonElement sunrise = sysObject.get("sunrise");
        JsonElement sunset = sysObject.get("sunset");
        JsonElement dt = gson.fromJson(jsonresp, JsonObject.class).get("dt");
        LocalDate date = new Date(sunrise.getAsLong() * 1000L).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate date1 = new Date(sunset.getAsLong() * 1000L).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = new Date(dt.getAsLong() * 1000L).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        currentweather.setSunrise(date);
        currentweather.setSunset(date1);
        currentweather.setForecastDate(date2);

        //temp calculations from kelvin to celsius
        JsonObject mainObject = gson.fromJson(jsonresp, JsonObject.class).get("main").getAsJsonObject();
        JsonElement temp = mainObject.get("temp");
        JsonElement feels_like = mainObject.get("feels_like");
        double tempCelsius = temp.getAsDouble() - 273.15;
        double feels_likeCelsius = feels_like.getAsDouble() - 273.15;

        //format to 2 decimals
        double tempCelsiusRounded = Math.round(tempCelsius * 100.0) / 100.0;
        double feels_likeCelsiusRounded = Math.round(feels_likeCelsius * 100.0) / 100.0;
        currentweather.setTemp(tempCelsiusRounded);
        currentweather.setFeels_like(feels_likeCelsiusRounded);









        return currentweather;
    }




}
