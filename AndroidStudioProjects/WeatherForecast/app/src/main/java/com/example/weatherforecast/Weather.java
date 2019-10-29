package com.example.weatherforecast;

public class Weather {

    private double hiTemp;
    private double loTemp;
    private WeatherPattern pattern;
    private static String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


    public Weather(double hiTemp, double loTemp, WeatherPattern pattern) {
        this.hiTemp = hiTemp;
        this.loTemp = loTemp;
        this.pattern = pattern;
    }

    public Weather() {
        hiTemp = 0;
        loTemp = 0;
        pattern = WeatherPattern.SUNNY;
    }

    public void setHiTemp(double hiTemp) {
        this.hiTemp = hiTemp;
    }

    public void setLoTemp(double loTemp) {
        this.loTemp = loTemp;
    }

    public double getHiTemp() {
        return hiTemp;
    }

    public double getLoTemp() {
        return loTemp;
    }

    public void setWeatherPattern(WeatherPattern pattern) {
        this.pattern = pattern;
    }

    public WeatherPattern getWeatherPattern() {
        return pattern;
    }

    public static String calculateDayOfWeek(int day) {
        int index = day % 6;
        return week[index];
    }
    enum WeatherPattern {
        SUNNY(R.drawable.sunny, "Sunny", 0),
        CLOUDY(R.drawable.cloudy, "Cloudy", 1),
        RAINY(R.drawable.rainy, "Rainy", 2),
        SNOWY(R.drawable.snowy, "Snowy", 3);

        private int resid;
        private int val;
        private String pattern;

        WeatherPattern(int resid, String pattern, int val) {
            this.pattern = pattern;
            this.resid = resid;
            this.val = val;
        }

        public int getId() {
            return resid;
        }

        public String getPattern() {
            return pattern;
        }

        public int getVal() {
            return val;
        }
    }
}
