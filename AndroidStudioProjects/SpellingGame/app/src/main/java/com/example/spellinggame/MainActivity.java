package com.example.spellinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButtonCorrect;
    RadioButton radioButtonSkip;
    RadioButton radioButtonIncorrect;
    Switch confirmSwitch;
    TextView wordPrompt;
    TextView scoreOfTwenty;
    TextView timeLeft;
    TextView gameOver;
    ImageView logo;
    ImageView background;
    ImageView llama;
    TextView switchLabel;
    Switch switchConfirm;
    Handler timerHandler = new Handler();
    Random random = new Random();
    Button startButton;
    DecimalFormat df = new DecimalFormat("0.00");
    Runnable timerInvert;
    private long miliseconds;
    private int percentCorrect;
    private final long SET_TIME = 1000 * (60 * 5);
    private long milisecondsLeft = SET_TIME;
    private Word prompted;
    private int score = 0;
    private ArrayList<Word> words = new ArrayList<>();
    private boolean stopped = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
            makeTextViewTimeLeft();
            makeTextViewWordPrompt();
            makeRadioGroup();
            makeSwitchConfirm();
            makeTextViewGameOver();
            makeStartButton();
            makeTextViewScore();
            makeImageViewLogo();
            makeLlamaImage();
            makeTextViewLabel();
            splashScreen();
            hideAll(wordPrompt, switchConfirm, radioGroup, radioButtonIncorrect, radioButtonCorrect, radioButtonSkip, gameOver, scoreOfTwenty);
//            showAll(startButton, llama);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    private void makeLlamaImage() {
        llama = findViewById(R.id.imageViewLlama);
    }

    private void makeRadioGroup() {
        radioGroup = findViewById(R.id.RadioGroupButtons);
        makeRadioButtonIncorrect();
        makeRadioButtonCorrect();
        makeRadioButtonSkip();
    }

    private void makeTextViewWordPrompt() {
        wordPrompt = findViewById(R.id.textViewWordDisplay);
    }

    private void makeTextViewGameOver() {
        gameOver = findViewById(R.id.textViewGameOver);
    }

    private void makeTextViewScore() {
        scoreOfTwenty = findViewById(R.id.textViewScore);
        scoreOfTwenty.setText("0/20\n%0");
    }

    private void makeTextViewLabel() {
        switchLabel = findViewById(R.id.textViewSwitchLabel);
    }

    private void startGame() {
        stopped = false;
        score = 0;
        scoreOfTwenty.setText("0/20\n%0");
        milisecondsLeft = SET_TIME;
        showAll(wordPrompt, switchConfirm, radioButtonSkip, radioButtonCorrect, radioButtonIncorrect, radioGroup, scoreOfTwenty, switchLabel);
        hideAll(startButton, gameOver, llama);
        words.clear();
        words.addAll(Arrays.asList(Word.values()));
        gameStart();
    }

    private void makeStartButton() {
        startButton = findViewById(R.id.buttonStart);
        try {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startGame();
                }
            });
        } catch (Exception e) {

        }
    }


    private void makeImageViewLogo() {
        logo = findViewById(R.id.imageViewLogo);
    }

    private void makeTextViewTimeLeft() {
        timeLeft = findViewById(R.id.textViewTimeLeft);
        timeLeft.setText("5:00");
    }

    private void makeSwitchConfirm() {
//        try {
            switchConfirm = findViewById(R.id.switchConfirm);
            switchConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkCorrect()) {
                        awardPoints();
                        advanceQuestion();
                    } else if (skipped()){
                        advanceQuestion();
                    } else if (nothingSelected()) {
                        switchConfirm.setChecked(false);
                    } else {
                        endGame("You Lost!\nWord you Misspelled:\n\t" + prompted);
//                        return;
                    }
                }
            });

            switchConfirm.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    if (checkCorrect()) {
                        awardPoints();
                        advanceQuestion();
                    } else if (skipped()){
                        advanceQuestion();
                    } else if (nothingSelected()) {
                        switchConfirm.setChecked(false);
                    } else {
                        endGame("You Lost!\nWord you Misspelled:\n\t" + prompted);
                        return true;
                    }
                    return false;
                }
            });

            switchConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if ( isChecked ) {
                        if (checkCorrect()) {
                            awardPoints();
                            advanceQuestion();
                        } else if (skipped()){
                            advanceQuestion();
                        } else if (nothingSelected()) {
                            switchConfirm.setChecked(false);
                        } else {
                            endGame("You Lost!\nWord you Misspelled:\n\t" + prompted);
                            return;
                        }
                    }
                }
            });

//        } catch (Exception e) {
//           e.printStackTrace();
//        }
    }

    private void makeRadioButtonSkip() {
        radioButtonSkip = findViewById(R.id.radioButtonSkip);
    }
    private void makeRadioButtonCorrect() {
        radioButtonCorrect = findViewById(R.id.radioButtonCorrect);
    }
    private void makeRadioButtonIncorrect() {
        radioButtonIncorrect = findViewById(R.id.radioButtonIncorrect);
    }

    private void updateTimer(long milisecondsLeft) {
        milisecondsLeft -= 1000;
        miliseconds = SET_TIME - milisecondsLeft;

        int minutes = (int) ( milisecondsLeft / 1000 ) / 60;
        int seconds = (int) (milisecondsLeft / 1000) % 60;

        timeLeft.setText(String.format("%02d:%02d", minutes, seconds));

        runTimer(milisecondsLeft);
        if (milisecondsLeft <= 0) {
            endGame("You Ran out of Time!\nYour Score:\n" + score +"/20");
            return;
        }
    }

    private boolean runTimer(final long millis) {


        if (millis > 0 && !stopped) {
            timerHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateTimer(millis);
                }
            }, 1000);
        }

        return true;


    }

    private void splashScreen() {
        hideAll(startButton, timeLeft, llama, radioGroup, radioButtonIncorrect, radioButtonCorrect, radioButtonSkip, switchConfirm, switchLabel);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                showAll(startButton, timeLeft, llama);
                hideAll(logo);
            }
        }, 3000);
        showAll(logo);
    }

    private void endGame(String message) {
        stopped = true;
//        if (score >= 19) {
//            gameOver.setText("You Won!");
//        } else {
//            gameOver.setText("Game Over\nWord you misspelled:\n " + prompted);
//        }
        gameOver.setText(message);
        hideAll(radioButtonCorrect, radioButtonIncorrect, radioButtonSkip, radioGroup, wordPrompt, switchConfirm);
        showAll(gameOver, startButton, scoreOfTwenty);
        words.clear();
    }

    private long getMiliseconds() {
        return miliseconds;
    }

    private long getMilisecondsLeft() {
        milisecondsLeft = SET_TIME - miliseconds;
        return milisecondsLeft;
    }

    public static void hideAll(View...views) {
        for ( View v : views ) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    public static void showAll(View...views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    private RadioButton determineCorrect(Word word) {
        return word.getCorrect() ? radioButtonCorrect : radioButtonIncorrect;
    }

    private boolean checkCorrect() {
        return ( (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()) == determineCorrect(prompted));
    }

    private boolean skipped() {
        return radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()) == radioButtonSkip;
    }

    private void awardPoints() {
        score++;
        if (score == 0) {
            scoreOfTwenty.setText("0/20\n%0");
        } else {
            scoreOfTwenty.setText(score + "/20\n%" + df.format(((((double) score/20)) * 100)));
        }
    }

    private void gameStart() {
        runTimer(SET_TIME);
        words.addAll(Arrays.asList(Word.values()));
        prompted = words.get(random.nextInt(Word.values().length - 1));
        wordPrompt.setText(prompted.getWord());
        switchConfirm.setChecked(false);
        radioGroup.clearCheck();
//        Toast.makeText(this, "Next Word!", Toast.LENGTH_SHORT).show();
    }

    private void advanceQuestion() {
        words.remove(prompted);
        Log.i("WordSizeCheck", words.size() + "");
        if (words.size() <= 1) {
            endGame("Game Over!\nYour score:\n\t" + score + "/20");
            return;
        }
        int index = words.size() - 1 > 0 ? words.size() - 1 : 0;
        prompted = words.get(random.nextInt(index));
        wordPrompt.setText(prompted.getWord());
        switchConfirm.setChecked(false);
        radioGroup.clearCheck();
        Toast.makeText(this, "Next Word!", Toast.LENGTH_SHORT).show();
    }

    private boolean nothingSelected() {
        RadioButton rb = findViewById(radioGroup.getCheckedRadioButtonId());
        return rb != radioButtonSkip && rb != radioButtonCorrect && rb != radioButtonIncorrect;
    }

    enum Word {


        Vaccuum("Vaccuum", false),
        Congradulate("Congradulate", false),
        Cemetery("Cemetery", true ),
        Prejudice("Prejudice", true),
        Rhythm("Rhythm", true),
        Cemetary("Cemetary", false),
        Conceed("Conceed", false),
        Occassionally("Occassionally", false),
        Secretery("Secretery", false),
        Succeed("Succeed", true),
        Inoculate("Inoculate", true),
        Comaraderie("Camaraderie", false),
        Harrass("Harrass", false),
        Relevant("Relevant", true),
        Recieve("Recieve", false),
        Conceive("Conceive", true),
        Secretary("Secretary", true),
        Definite("Definite", true),
        Hygiene("Hygiene", true),
        Irrelevant("Irrelevant", true),
        Innoculate("Innoculate", false),
        Camoflage("Camoflage", false),
        Allege("Allege", true),
        Dumbbell("Dumbbell", true),
        Tomorow("Tomorow", false),
        Priviledge("Priviledge", false),
        Definately("Definately", false),
        Camaraderie("Camaraderie", true),
        Whether("Whether", true),
        Quarentine("Quarentine", false),
        Questionnaire("Questionnaire", true),
        Underrate("Underrate", true),
        Embarrass("Embarrass", true),
        Fascinating("Fascinating", true),
        Privilege("Privilege", true);

        String word;
        boolean spelledCorrectly;

         Word(String word, boolean spelledCorrectly) {
            this.word = word;
            this.spelledCorrectly = spelledCorrectly;
        }

        public String getWord() {
            return word;
        }

        public boolean getCorrect() {
            return spelledCorrectly;
        }


    }
}
