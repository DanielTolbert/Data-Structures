package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView textViewtitle;
    Button buttonStartGame;
    Button buttonResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMiscellaneousViews();
        createButtonResults();
        createButtonStartGame();
    }

    private void createMiscellaneousViews() {
        textViewtitle = findViewById(R.id.textViewHomeTitle);
    }

    private void createButtonStartGame() {
        buttonStartGame = findViewById(R.id.buttonBeginGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createButtonResults() {
        buttonResults = findViewById(R.id.buttonCurrentStandings);
        buttonResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), StandingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
