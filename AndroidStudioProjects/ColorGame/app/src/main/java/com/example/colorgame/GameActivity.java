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
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collector;

public class GameActivity extends AppCompatActivity {

    TextView textViewRed;
    TextView textViewGreen;
    TextView textViewBlue;

    Button buttonSubmit;


    EditText editTextRed;
    EditText editTextGreen;
    EditText editTextBlue;

    public static final String GUESSES_KEY = "guesses";
    public static final String ANSWERS_KEY = "answers";

    Random random = new Random();

    ArrayList<String> answers;
    ArrayList<String> guesses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        createMiscellaneousViews();
        makeButtonSubmit();
        createEditTexts();
        answers = setBackgroundColor(random.nextInt(255), random.nextInt(255), random.nextInt(255));
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

//        editTextGreen.setBackgroundColor(Color.WHITE);
//        editTextRed.setBackgroundColor(Color.WHITE);
//        editTextBlue.setBackgroundColor(Color.WHITE);
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
                            editText.setText(s.toString().substring(0, start));
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

    private ArrayList<String> makeGuessArray() {
        ArrayList<String> rgbGuess = new ArrayList<>();

        rgbGuess.add(Double.parseDouble(editTextRed.getText().toString()) + "");
        rgbGuess.add(Double.parseDouble(editTextGreen.getText().toString()) + "");
        rgbGuess.add(Double.parseDouble(editTextBlue.getText().toString()) + "");

        return rgbGuess;
    }

    private void makeButtonSubmit() {
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((editTextBlue.getText().toString().isEmpty() ||
                        editTextGreen.getText().toString().isEmpty() ||
                            editTextRed.getText().toString().isEmpty())) {
                    Toast.makeText(getBaseContext(), R.string.warning, Toast.LENGTH_SHORT);
                } else {
                    guesses = makeGuessArray();
                    Intent intent = new Intent(getBaseContext(), ResultsActivity.class);
                    String[] guess = new String[3];
                    String[] ans = new String[3];

                    for(int i = 0; i < 3; i ++) {
                        guess[i] = guesses.get(i);
                        ans[i] = answers.get(i);
                    }

                    String a = ans[0] + " " + ans[1] + " " + ans[2];
                    String g = guess[0] + " " + guess[1] + " " + guess[2];


                    intent.putExtra(GameActivity.GUESSES_KEY, g);
                    intent.putExtra(GameActivity.ANSWERS_KEY, a);
                    startActivity(intent);
                }
            }
        });
    }

    private ArrayList<String> setBackgroundColor(int r, int g, int b) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(r, g, b));

        ArrayList<String> rgbAnswer = new ArrayList<>();

        rgbAnswer.add(Double.valueOf(r) + "");
        rgbAnswer.add(Double.valueOf(g) + "");
        rgbAnswer.add(Double.valueOf(b) + "");

        return rgbAnswer;
    }
}
