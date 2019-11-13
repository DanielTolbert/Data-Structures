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

    String[] guesses = new String[3];
    String[] answers = new String[3];
    String ans;
    String guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        receiveData();
        createMiscellaneousViews();
//        createMiscellaneousViews();
    }

    private void receiveData() {
        Intent intent = getIntent();
        ans = intent.getStringExtra(GameActivity.ANSWERS_KEY);
        guess = intent.getStringExtra(GameActivity.GUESSES_KEY);



        answers = ans.split(" ");
        guesses = guess.split(" ");

        Log.i("TVR", answers[0] + "");

    }

    private double getDistance() {

        return Math.pow(Math.pow(Double.valueOf(answers[0])
                - Double.valueOf(guesses[0]), 3) +
                Math.pow(Double.valueOf(answers[1]) - Double.valueOf(guesses[1]), 3) +
                Math.pow(Double.valueOf(answers[2]) - Double.valueOf(guesses[2]), 3),(1d/2d));
    }

    private void createMiscellaneousViews() {
        textViewResults = findViewById(R.id.textViewResults);
        textViewRedDistance = findViewById(R.id.textViewRedDistance);
        textViewBlueDistance = findViewById(R.id.textViewBlueDistance);
        textViewGreenDistance = findViewById(R.id.textViewGreenDistance);

        Log.i("TVRD", textViewRedDistance.toString());
//        Log.i("TVGD", textViewBlueDistance.toString());
        Log.i("TVBD", textViewGreenDistance.toString());

        textViewResults.setText("You were " + getDistance() + " off.");
        textViewRedDistance.setText(Double.valueOf(answers[0])- Double.valueOf(guesses[0]) + "");
        textViewGreenDistance.setText(Double.valueOf(answers[1]) - Double.valueOf(guesses[1]) + "");
        textViewBlueDistance.setText(Double.valueOf(answers[2]) - Double.valueOf(guesses[2] )+ "");

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb((int)(double)Double.valueOf(answers[0]),(int)(double)Double.valueOf(answers[1]),
                (int)(double)Double.valueOf(answers[2])));
    }

    private void displayResults() {

    }
}
