package com.dmooers.data_munging;


import java.util.Optional;


class WeatherSpreadEntry {
    final private int day;
    final private int maxTemp;
    final private int minTemp;

    public static class Reader {

        public static Optional<WeatherSpreadEntry> read(String[] tokens) {

            try {
                int day = Integer.parseInt(tokens[0]);
                int maxTemp = Integer.parseInt(tokens[1]);
                int minTemp = Integer.parseInt(tokens[2]);



                return Optional.of(new WeatherSpreadEntry(day, maxTemp, minTemp));
            } catch (Exception e) {
                return Optional.empty();
            }

        }
    }

    public WeatherSpreadEntry(int day, int maxTemp, int minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public int getDay() {
        return day;
    }

    public int getSpread() {
        return maxTemp - minTemp;
    }

    public String toString() {
        return String.format("Day: %d Spread: %d", day, getSpread());
    }
}
