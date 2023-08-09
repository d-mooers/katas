package com.dmooers.data_munging;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class App {

    private static void runWeather(String filePath) {
        DatReader reader = new DatReader("[^0-9]+");

        List<WeatherSpreadEntry> weatherEntries = reader.read(new File((filePath)), WeatherSpreadEntry.Reader::read);

        WeatherSpreadEntry min = weatherEntries.stream().min(Comparator.comparingInt(WeatherSpreadEntry::getSpread)).get();

        System.out.println(min);
    }

    private static void runFootball(String filePath) {
        DatReader reader = new DatReader();

        List<FootballEntry> footballEntries = reader.read(new File((filePath)), FootballEntry.Reader::read);

        FootballEntry min = footballEntries.stream().min(Comparator.comparingInt(FootballEntry::getGoalsSpread)).get();

        System.out.println(min);
    }
    public static void main(String[] args) throws Exception {
        String command = args[0];
        String filepath = args[1];
        switch (command) {
            case "w":
                runWeather(filepath);
                break;
            case "f":
                runFootball(filepath);
                break;
            default:
                throw new Exception("Unexpected value");
        }
    }
}
