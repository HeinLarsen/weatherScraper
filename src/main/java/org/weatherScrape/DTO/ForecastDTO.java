package org.weatherScrape.DTO;

public class ForecastDTO {

    private String date;
    private DayDTO day;
    private NightDTO night;

    public ForecastDTO(String date, DayDTO day, NightDTO night) {
        this.date = date;
        this.day = day;
        this.night = night;
    }

    public String getDate() {
        return date;
    }

    public DayDTO getDay() {
        return day;
    }

    public NightDTO getNight() {
        return night;
    }
}
