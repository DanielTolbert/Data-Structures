package com.example.weatherforecast;

public class Weather {

    private double hiTemp;
    private double loTemp;
    private WeatherPattern pattern;

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

    public void setWeatherPattern(WeatherPattern pattern) {
        this.pattern = pattern;
    }

    enum WeatherPattern {
        SUNNY,
        RAINY,
        SNOWY,
        CLOUDY;
    }
}
