package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    double[] rgbArrayGuesses = (getIntent().getDoubleArrayExtra("RGB Array Guess"));
    double[] rgbArrayAnswers = getIntent().getDoubleArrayExtra("RBG Array Answers");

    TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        createMiscellaneousViews();
    }

    private double getDistance() {
        return Math.pow(Math.pow(rgbArrayAnswers[0] - rgbArrayGuesses[0], 3) +
                Math.pow(rgbArrayAnswers[1] - rgbArrayGuesses[1], 3) +
                Math.pow(rgbArrayAnswers[2] - rgbArrayGuesses[2], 3),(1d/3d));
    }

    private void createMiscellaneousViews() {
        textViewResults = findViewById(R.id.textViewResults);
        textViewResults.setText("You were " + getDistance() + " off.");
    }

    private void displayResults() {

    }
}
