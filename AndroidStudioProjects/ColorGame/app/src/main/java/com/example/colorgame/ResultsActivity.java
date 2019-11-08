package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {



    TextView textViewResults;
    TextView textViewRedDistance;
    TextView textViewGreenDistance;
    TextView textViewBlueDistance;

    double[] guesses = new double[3];
    double[] answers = new double[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        createMiscellaneousViews();
        receiveData();
        createMiscellaneousViews();
    }

    private void receiveData() {
        Intent intent = getIntent();
        guesses[0] = intent.getDoubleArrayExtra(GameActivity.GUESSES_KEY)[0];
        guesses[1] = intent.getDoubleArrayExtra(GameActivity.GUESSES_KEY)[1];
        guesses[2] = intent.getDoubleArrayExtra(GameActivity.GUESSES_KEY)[2];
        answers[0] = intent.getDoubleArrayExtra(GameActivity.ANSWERS_KEY)[0];
        answers[1] = intent.getDoubleArrayExtra(GameActivity.ANSWERS_KEY)[1];
        answers[2] = intent.getDoubleArrayExtra(GameActivity.ANSWERS_KEY)[2];
    }

    private double getDistance() {

        return Math.pow(Math.pow(answers[0]
                - guesses[0], 3) +
                Math.pow(answers[1] - guesses[1], 3) +
                Math.pow(answers[2] - guesses[2], 3),(1d/2d));
    }

    private void createMiscellaneousViews() {
        textViewResults = findViewById(R.id.textViewResults);
        textViewRedDistance = findViewById(R.id.textViewRedDistance);
        textViewBlueDistance = findViewById(R.id.textViewBlueLabel);
        textViewGreenDistance = findViewById(R.id.textViewGreenDistance);


        textViewResults.setText("You were " + getDistance() + " off.");
        textViewRedDistance.setText(answers[0] - guesses[0] + "");
        textViewGreenDistance.setText(answers[1] - guesses[1] + "");
        textViewBlueDistance.setText(answers[2] - guesses[2] + "");

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb((int)answers[0],(int)answers[1],(int)answers[2]));
    }

    private void displayResults() {

    }
}
