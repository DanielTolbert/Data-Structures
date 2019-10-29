package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonAdvance;
    Button buttonGoBack;
    TextView textViewDayName;
    TextView textViewTemperatures;
    TextView textViewWeatherPattern;
    ImageView imageViewWeather;
    Random random = new Random();
    int currentDay = 0;
    int originalDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        makeButtonAdvance();
        makeButtonGoBack();
    }

    private Weather forecastWeather(int day) {
        random.setSeed(currentDay);
        int hi = random.nextInt(120);
        int low = random.nextInt(hi);

        boolean percipitating = random.nextBoolean();
        Weather.WeatherPattern pattern;
        if (percipitating) {
            pattern = hi <= 32 ? Weather.WeatherPattern.SNOWY : Weather.WeatherPattern.RAINY;
        } else {
           pattern = random.nextBoolean() ? Weather.WeatherPattern.SUNNY : Weather.WeatherPattern.CLOUDY;
        }
        return new Weather(hi, low, pattern);
    }

    private void makeMiscellaneousViews() {
        textViewDayName = findViewById(R.id.textViewDayName);
        textViewTemperatures = findViewById(R.id.textViewTemperatures);
        textViewWeatherPattern = findViewById(R.id.textViewWeatherPattern);
        imageViewWeather = findViewById(R.id.imageViewWeather);
    }

    private void updateWeather() {
        Weather weather = forecastWeather(currentDay);
        textViewDayName.setText(Weather.calculateDayOfWeek(currentDay));
        textViewWeatherPattern.setText(weather.getWeatherPattern().getPattern());
        textViewTemperatures.setText("High: " + weather.getHiTemp() + "\nLow: " + weather.getLoTemp());
        imageViewWeather.setImageResource(weather.getWeatherPattern().getId());
    }

    private void makeButtonAdvance() {
        buttonAdvance = findViewById(R.id.buttonAdvance);
        buttonAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentDay != originalDay) {
                    currentDay--;
                    updateWeather();
                }
            }
        });
    }

    private void makeButtonGoBack() {
        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDay++;
            }
        });
    }


}
