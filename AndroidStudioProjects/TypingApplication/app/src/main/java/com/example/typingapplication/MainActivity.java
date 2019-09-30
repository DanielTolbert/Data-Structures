package com.example.typingapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private EditText plainText;
    private TextView displayed;
    private String[] wordsTyped = {""};
    private String displayedWord = "";
    private ImageView morningStarLogo;
//    private ImageView wallpaper = findViewById(R.id.imageViewWallpaper);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginProgram();
    }

    private void beginProgram() {
        createEditText();
        createSeekBar();
        createTextView();
        createImageView();
        splashScreen(3);
    }

    private void hideAll(View...views) {
        for (View v : views) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    private void showAll(View...views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    private void createSeekBar() {
        seekBar = findViewById(R.id.seekBarWordProgress);
        seekBar.setMax(wordsTyped.length);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    updateDisplayedWord(wordsTyped[seekBar.getProgress()]);
                } catch (Exception e) {

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void createImageView() {
        morningStarLogo = findViewById(R.id.imageViewLogo);
    }

    private void createEditText() {
        plainText = findViewById(R.id.editTextTypeBox);

        plainText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                updateSeekBar();
                return false;
            }
        });

    }

    private void createTextView() {
        displayed = findViewById(R.id.textViewDisplayedWord);
    }

    private void splashScreen(int seconds) {
        hideAll(seekBar, plainText, displayed);
        showAll(morningStarLogo);
        runTimer(seconds * 1000);
    }

    private void updateSeekBar() {
        seekBar.setMax(getWordsTyped().length - 1);
    }

    private String[] getWordsTyped() {
        wordsTyped = plainText.getText().toString().split("\\s+");
        return wordsTyped;
    }

    private void updateDisplayedWord(String pWord) {
        displayedWord = pWord;
        displayed.setText("Word " + ( seekBar.getProgress() + 1 ) + ": " + displayedWord);
    }

    private String getDisplayedWord() {
        return displayedWord;
    }

    private void runTimer(long millis) {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideAll(morningStarLogo);
                showAll(plainText, displayed, seekBar);
            }
        }, millis);
        showAll(morningStarLogo);
    }

}
