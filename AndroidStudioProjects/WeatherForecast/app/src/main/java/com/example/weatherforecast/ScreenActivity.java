package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        splashScreen(3);
    }

    private void splashScreen(int seconds) {
        long millis = seconds * 1000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeActivity();
            }
        }, millis);
    }

    private void changeActivity() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
//        finish();
    }

}
