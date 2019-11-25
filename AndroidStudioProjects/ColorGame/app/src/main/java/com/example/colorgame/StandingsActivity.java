package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class StandingsActivity extends AppCompatActivity {

    ArrayList<Color> standingsColor = new ArrayList<>();

    Button buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        receiveData();
        makeTextViews();
        createMiscellaneousViews();
    }

    private void receiveData() {
        Collections.sort(standingsColor);
        Collections.reverse(standingsColor);
        standingsColor.add(new Color(255, 255, 255));
        standingsColor.add(new Color(255, 255, 255));
        standingsColor.add(new Color(255, 255, 255));
        standingsColor.add(new Color(255, 255, 255));
        standingsColor.addAll(Color.getStandings());
    }

    private void createMiscellaneousViews() {
        buttonHome = findViewById(R.id.buttonHomeFromStandings);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void makeTextViews() {
        LinearLayout layout = findViewById(R.id.linearLayout);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        int index = 0;
        for (Color c : standingsColor) {
            TextView textView = new TextView(this);
            textView.setText( decimalFormat.format(c.getDistance()));
            textView.setTextSize(24);
            textView.setPadding(0,0,0,0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setBackgroundColor(android.graphics.Color.rgb(c.getR(), c.getG(), c.getB()));
            layout.addView(textView);
            index ++;
        }



        Log.i("SCT", Color.getStandings().size() + "");

    }

}
