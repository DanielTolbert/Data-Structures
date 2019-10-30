package com.example.weatherforecast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonAdvance;
    Button buttonGoBack;
    Button buttonReturn;
    TextView textViewDayName;
    TextView textViewTemperatures;
    TextView textViewDayNumber;
    TextView textViewWeatherPattern;
    ImageView imageViewWeather;
    Random fRandom = new Random();
    Random random = new Random();
    int currentDay = 0;
    int originalDay = 0;
    int scalar = fRandom.nextInt(100);
    int weatherIds[] = {R.string.sunny, R.string.cloudy, R.string.rainy, R.string.snowy};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeButtonAdvance();
        makeButtonGoBack();
        makeButtonReturn();
        makeMiscellaneousViews();
        updateWeather();
    }

    private Weather forecastWeather(int day) {

        buttonGoBack.setVisibility(originalDay == currentDay ? View.GONE : View.VISIBLE);
        buttonReturn.setVisibility(originalDay == currentDay ? View.GONE : View.VISIBLE);

        random.setSeed(day + scalar);
        int hi = random.nextInt(120);
        int lowbound = 7;
        int num = random.nextInt(lowbound);
        int low = hi - num < 0 ? 0 : hi - num;

        boolean precipitating = random.nextBoolean();
        Weather.WeatherPattern pattern;
        if (precipitating) {
            pattern = hi <= 32 ? Weather.WeatherPattern.SNOWY : Weather.WeatherPattern.RAINY;
        } else {
           pattern = random.nextBoolean() ? Weather.WeatherPattern.SUNNY : Weather.WeatherPattern.CLOUDY;
        }
        return new Weather(hi, low, pattern);
    }

    private void makeButtonReturn() {
        buttonReturn = findViewById(R.id.buttonOriginalDay);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDay = originalDay;
                updateWeather();
            }
        });
    }

    private void makeMiscellaneousViews() {
        textViewDayName = findViewById(R.id.textViewDayName);
        textViewTemperatures = findViewById(R.id.textViewTemperatures);
        textViewWeatherPattern = findViewById(R.id.textViewWeatherPattern);
        imageViewWeather = findViewById(R.id.imageViewWeather);
        textViewDayNumber = findViewById(R.id.textViewDayNumber);
    }

    private void updateWeather() {
        Weather weather = forecastWeather(currentDay);
        textViewDayName.setText(Weather.calculateDayOfWeek(currentDay));
        textViewWeatherPattern.setText( weatherIds[weather.getWeatherPattern().getVal()] );
        textViewTemperatures.setText((String)getResources().getText(R.string.High) + ": " + weather.getHiTemp() + "°F" + "\n" + (String)getResources().getString(R.string.Low) +": "+ weather.getLoTemp() + "°F");
        imageViewWeather.setImageResource(weather.getWeatherPattern().getId());
        textViewDayNumber.setText(getResources().getString(R.string.Day)+": " + (currentDay + 1));

    }

    private void makeButtonAdvance() {
        buttonAdvance = findViewById(R.id.buttonAdvance);
        buttonAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDay++;
                Log.i("WeatherApp", currentDay + "");
                updateWeather();
            }
        });
    }

    private void makeButtonGoBack() {
        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentDay != originalDay) {
                    currentDay--;
                    Log.i("WeatherApp", currentDay + "");
                    updateWeather();
                }
            }
        });
    }


}
