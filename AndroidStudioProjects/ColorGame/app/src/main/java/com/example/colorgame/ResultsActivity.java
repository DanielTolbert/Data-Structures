package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.colorgame.Color;

import java.text.DecimalFormat;

public class ResultsActivity extends AppCompatActivity {



    TextView textViewResults;
    TextView textViewRedDistance;
    TextView textViewGreenDistance;
    TextView textViewBlueDistance;

    EditText editTextR;
    EditText editTextG;
    EditText editTextB;

    Button buttonHome;

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
        createStanding();
    }

    private void receiveData() {
        Intent intent = getIntent();
        ans = intent.getStringExtra(GameActivity.ANSWERS_KEY);
        guess = intent.getStringExtra(GameActivity.GUESSES_KEY);



        answers = ans.split(" ");
        guesses = guess.split(" ");

        Log.i("TVR", answers[0] + "");

    }

    private void createStanding() {
        com.example.colorgame.Color color = new Color((int) Double.parseDouble(answers[0]),
                (int) Double.parseDouble(answers[1]),
                (int) Double.parseDouble(answers[2]));
        color.calcDistance((int)Double.parseDouble(guesses[0]), (int)Double.parseDouble(guesses[1]), (int)Double.parseDouble(guesses[2]));
        Color.standings.add(color);
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

        editTextB = findViewById(R.id.editTextBlueInput2);
        editTextR = findViewById(R.id.editTextRedInput2);
        editTextG = findViewById(R.id.editTextGreenInput2);

        editTextG.setText(guesses[1]);
        editTextR.setText(guesses[0]);
        editTextB.setText(guesses[2]);

        Log.i("TVRD", textViewRedDistance.toString());
//        Log.i("TVGD", textViewBlueDistance.toString());
        Log.i("TVBD", textViewGreenDistance.toString());

        String format = "0.00";
        DecimalFormat decimalFormat = new DecimalFormat(format);

        textViewResults.setText("You were \n" + decimalFormat.format(getDistance()) + " off.");
        textViewRedDistance.setText(Double.valueOf(answers[0])- Double.valueOf(guesses[0]) + "");
        textViewGreenDistance.setText(Double.valueOf(answers[1]) - Double.valueOf(guesses[1]) + "");
        textViewBlueDistance.setText(Double.valueOf(answers[2]) - Double.valueOf(guesses[2] )+ "");

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(android.graphics.Color.rgb((int)(double)Double.valueOf(answers[0]),(int)(double)Double.valueOf(answers[1]),
                (int)(double)Double.valueOf(answers[2])));

        buttonHome = findViewById(R.id.buttonHomeFromResults);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayResults() {

    }
}
