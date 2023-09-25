package org.weatherScrape.helper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static LocalTime getLocalTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return LocalTime.parse(time, formatter);
    }

    public static String charVanish(String str, String vanish) {
        // Bang - and the char is gone!
        return str.replace(vanish, "");
    }


}
