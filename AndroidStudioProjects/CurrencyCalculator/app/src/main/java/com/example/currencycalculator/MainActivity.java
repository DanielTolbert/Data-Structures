package com.example.currencycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button buttonConvert;
    EditText editTextDollar;
    TextView textViewYen;

    private final double DOLLAR_TO_YEN_RATE = 100.00;
    private DecimalFormat FORMAT = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createViews();

    }

    private void createViews() {
        buttonConvert = findViewById(R.id.button);
        editTextDollar = findViewById(R.id.editTextEnterAmount);
        textViewYen = findViewById(R.id.TextViewYen);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });

        editTextDollar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextDollar.getText().toString().contains(".")) {

                    if (editTextDollar.getText().toString().substring(editTextDollar.getText().toString().indexOf(".")).length() > 3) {
                        String toRemove = editTextDollar.getText().toString().substring(0, editTextDollar.getText().toString().indexOf(".") + 3);
                        editTextDollar.setText(toRemove);
                        editTextDollar.setSelection(start);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {

//                } catch (Exception e) {
//
//                }
            }
        });
    }

    private int findCursorPosition(String oldString, String newString) {
        for (int i = 0; i < newString.length(); i ++ ) {
            if (oldString.charAt(i) != newString.charAt(i)) {
                return i;
            }
        }
        return 0;
    }

    private void convert() {

        double dollar = ((editTextDollar.getText().toString().contains(".") && editTextDollar.getText().toString().length() == 1) || editTextDollar.getText().toString().equals("")) ? 0.00 : Double.parseDouble(editTextDollar.getText().toString());
        String yen = ("" + FORMAT.format(dollar * DOLLAR_TO_YEN_RATE));
        textViewYen.setText(yen);
    }
}
