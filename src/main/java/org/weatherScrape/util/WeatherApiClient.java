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
import java.util.Date;
import java.util.List;

public class WeatherApiClient {

    static HttpClient httpClient = HttpClient.newHttpClient();





    public static CurrentWeather getWeatherDateNow(String cityName) throws IOException, InterruptedException {
        String apiKey = "a4e9445f16deb18de54bee308714ee5c";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=" + apiKey;


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonresp = response.body();





        return jsonresp;
    }

    public static List<Date> convertJSONRespToDates(String JsonResponse){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(sunrise, JsonObject.class);
        JsonObject sysObject = jsonObject.getAsJsonObject("sys");
        JsonElement sunrise1 = sysObject.get("sunrise");
        JsonElement sunset1 = sysObject.get("sunset");
        JsonElement dt1 = jsonObject.get("dt");
        Date date = new Date(sunrise1.getAsLong() * 1000L);
        Date date1 = new Date(sunset1.getAsLong() * 1000L);
        Date date2 = new Date(dt1.getAsLong() * 1000L);

        return List.of(date, date1, date2);

    }


}
