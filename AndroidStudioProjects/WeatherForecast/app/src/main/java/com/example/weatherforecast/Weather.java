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
        SUNNY(R.drawable.sunny, R.string.sunny, 0),
        CLOUDY(R.drawable.cloudy, R.string.cloudy, 1),
        RAINY(R.drawable.rainy, R.string.rainy, 2),
        SNOWY(R.drawable.snowy, R.string.snowy, 3);

        private int resid;
        private int val;
        private int patternId;

        WeatherPattern(int resid, int patterId, int val) {
            this.patternId = patternId;
            this.resid = resid;
            this.val = val;
        }

        public int getId() {
            return resid;
        }

        public int getPatternId() {
            return patternId;
        }

        public int getVal() {
            return val;
        }
    }
}
