package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.FontRequest;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collector;

public class GameActivity extends AppCompatActivity {

    TextView textViewRed;
    TextView textViewGreen;
    TextView textViewBlue;

    Button buttonSubmit;

    EditText editTextRed;
    EditText editTextGreen;
    EditText editTextBlue;

    Random random = new Random();

    double[] rgbGuess = {0d,0d,0d};
    double[] rgbAnswer = {0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        createMiscellaneousViews();
        makeButtonSubmit();
        createEditTexts();
        setBackgroundColor(random.nextInt(255), random.nextInt(255), random.nextInt(255));
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

        makeEditTextListeners(editTextBlue, editTextGreen, editTextRed);

        editTextGreen.setBackgroundColor(Color.WHITE);
        editTextRed.setBackgroundColor(Color.WHITE);
        editTextBlue.setBackgroundColor(Color.WHITE);
    }

    private void makeEditTextListeners(final EditText...texts) {
        ArrayList<EditText> editTexts = new ArrayList<>();
        editTexts.add(texts[0]);
        editTexts.add(texts[1]);
        editTexts.add(texts[2]);
        final ArrayList<EditText> editTextArrayList = editTexts;
        for (final EditText editText : texts) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!editText.getText().toString().isEmpty()) {
                        if ( Double.parseDouble(editText.getText().toString()) > 255) {
                            editText.setText("255");
                            editText.setSelection(start);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private void makeGuessArray() {
        rgbGuess[0] = Double.parseDouble(editTextRed.getText().toString());
        rgbGuess[1] = Double.parseDouble(editTextGreen.getText().toString());
        rgbGuess[2] = Double.parseDouble(editTextBlue.getText().toString());
    }

    private void makeButtonSubmit() {
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeGuessArray();
                Intent intent = new Intent(getBaseContext(), ResultsActivity.class);
                ArrayList<Double> rgbs = new ArrayList<>();
                for (double d : rgbAnswer) {
                    rgbs.add(d);
                }

                for (double d : rgbGuess) {
                    rgbs.add(d);
                }
                Log.i("Array Check", rgbs.toArray().toString());
                intent.putExtra("rgb answers guesses", rgbs.toArray());
                startActivity(intent);
            }
        });
    }

    private void setBackgroundColor(int r, int g, int b) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(r, g, b));

        rgbAnswer[0] = r;
        rgbAnswer[1] = g;
        rgbAnswer[2] = b;
    }
}
