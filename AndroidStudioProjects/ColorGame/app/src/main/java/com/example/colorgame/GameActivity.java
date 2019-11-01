package com.example.colorgame;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textViewRed;
    TextView textViewGreen;
    TextView textViewBlue;

    Button buttonSubmit;

    EditText editTextRed;
    EditText editTextGreen;
    EditText editTextBlue;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//        setBackgroundColor(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        createMiscellaneousViews();
        makeButtonSubmit();
    }

    private void createMiscellaneousViews() {
        textViewBlue = findViewById(R.id.textViewBlueLabel);
        textViewRed = findViewById(R.id.textViewRedLabel);
        textViewGreen = findViewById(R.id.textViewGreenLabel);
    }

    private void createEditTexts() {
        editTextBlue = findViewById(R.id.editTextBlueInput);
        editTextGreen = findViewById(R.id.editTextGreenInput);
        editTextRed = findViewById(R.id.editTextRedInput);

        editTextBlue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Double.parseDouble((String)s) > 255) {
                    editTextBlue.setText("255");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextRed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Double.parseDouble((String)s) > 255) {
                    editTextRed.setText("255");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextGreen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Double.parseDouble((String)s) > 255) {
                    editTextGreen.setText("255");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void makeButtonSubmit() {
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                
            }
        });
    }

    private void setBackgroundColor(int r, int g, int b) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.argb(0, r, g, b));
    }
}
