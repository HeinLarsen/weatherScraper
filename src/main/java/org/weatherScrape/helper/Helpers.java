package org.weatherScrape.helper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helpers {

    public static LocalTime getLocalTime(String time) {
        Locale.setDefault(Locale.US);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return LocalTime.parse(time, formatter);
    }

    public static String charVanish(String str, String vanish) {
        // Bang - and the char is gone!
        return str.replace(vanish, "");
    }


}
