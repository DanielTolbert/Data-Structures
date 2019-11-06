package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {



    TextView textViewResults;
    double[] rgbValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        createMiscellaneousViews();
        receiveData();
//        createMiscellaneousViews();
    }

    private void receiveData() {
        Intent intent = getIntent();
        double[] doubles = {1,1,1,1};
        Log.i("Array Check", doubles.toString());

    }

    private double getDistance() {
        return Math.pow(Math.pow(rgbValues[0] - rgbValues[3], 3) +
                Math.pow(rgbValues[1] - rgbValues[4], 3) +
                Math.pow(rgbValues[2] - rgbValues[5], 3),(1d/2d));
    }

    private void createMiscellaneousViews() {
        textViewResults = findViewById(R.id.textViewResults);
        textViewResults.setText("You were " + getDistance() + " off.");
    }

    private void displayResults() {

    }
}
