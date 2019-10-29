package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonAdvance;
    Button buttonGoBack;
    TextView textViewForecast;
    Random random = new Random();
    int currentDay = 0;
    int originalDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
    }

    private int calculateWeather(int day) {
        random.setSeed(currentDay);
        return random.nextInt(120);

    }

    private void makeTextViewForecast() {
        textViewForecast = findViewById(R.id.textViewWeather);
        textViewForecast.setText(calculateWeather(currentDay));
    }

    private void makeButtonAdvance() {
        buttonAdvance = findViewById(R.id.buttonAdvance);
        buttonAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentDay != originalDay) {
                    currentDay--;
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
