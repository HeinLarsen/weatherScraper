package org.weatherScrape.DTO;

public class CurrentWeatherDTO {

    private String date;
    private int temperature;
    private int cloudCover;
    private int windGust;
    private int windSpeed;
    private String windDirection;
    private int thunderProbability;
    private int precipitation;
    private int precipitationProbability;
    private String statement;
    private int uvIndex;

    public CurrentWeatherDTO(String date, int temperature, int cloudCover, int windGust, int windSpeed, String windDirection, int thunderProbability, int precipitation, int precipitationProbability, String statement, int uvIndex) {
        this.date = date;
        this.temperature = temperature;
        this.cloudCover = cloudCover;
        this.windGust = windGust;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.thunderProbability = thunderProbability;
        this.precipitation = precipitation;
        this.precipitationProbability = precipitationProbability;
        this.statement = statement;
        this.uvIndex = uvIndex;
    }

    public String getDate() {
        return date;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public int getWindGust() {
        return windGust;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public int getThunderProbability() {
        return thunderProbability;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public int getPrecipitationProbability() {
        return precipitationProbability;
    }

    public String getStatement() {
        return statement;
    }

    public int getUvIndex() {
        return uvIndex;
    }
}
