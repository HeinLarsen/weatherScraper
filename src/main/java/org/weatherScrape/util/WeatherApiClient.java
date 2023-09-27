package org.weatherScrape.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApiClient {

    static HttpClient httpClient = HttpClient.newHttpClient();



   /* public static String getWeatherDataNow() throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=Skagen,dk&APPID=a4e9445f16deb18de54bee308714ee5c"))
                .build();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(getResponse.body());




        return null;
    }*/

    public static String getWeatherDateNow(String cityName) throws IOException, InterruptedException {
        String apiKey = "a4e9445f16deb18de54bee308714ee5c";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=" + apiKey;


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonresp = response.body();

        return jsonresp;
    }


}
