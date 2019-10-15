package com.example.billingapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private Spinner tipAmountSpin;
    private EditText amountDueText;
    private TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createTextView();
        createTextViewTotal();
    }

    private void createSpinner() {
        tipAmountSpin = findViewById(R.id.spinnerTipAmount);

        ArrayList<String> tips = new ArrayList<>(41);

        for (int i = 0; i <= 40; i ++) {
            tips.add(String.valueOf(i * 0.5));
        }

        ArrayAdapter<String> tipsAdappted = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tips);
        tipAmountSpin.setAdapter(tipsAdappted);

        tipAmountSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                formatText();
                if (amountDueText.getText().toString().length() > 0) {
                    calculateTotal();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                formatText();
            }
        });


    }

    private void createTextView() {
        amountDueText = findViewById(R.id.textViewAmountDue);
        amountDueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (amountDueText.getText().toString().contains(".")) {
                    if (amountDueText.getText().toString().substring(amountDueText.getText().toString().indexOf(".")).length() > 3) {
                        String toRemove = amountDueText.getText().toString().substring(0, amountDueText.getText().toString().indexOf(".") + 3);
                        amountDueText.setText(toRemove);
                        amountDueText.setSelection(start);
                    }
                }

                try {
                    calculateTotal();
                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void createTextViewTotal() {
        textViewTotal = findViewById(R.id.textViewTotal);
    }

    private boolean calculateTotal() {
        DecimalFormat df = new DecimalFormat("0.00");

        double due = amountDueText.getText().length() > 0 ? Double.parseDouble(amountDueText.getText().toString()) : 0.0;
        double tip = Double.parseDouble(tipAmountSpin.getSelectedItem().toString());

        double total = (due * (tip * 0.01) + due );


        String stringTotal = df.format(total);

        textViewTotal.setText(stringTotal);
        return true;
    }

    private void formatText() {
        String formatPattern = "###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(formatPattern);
        try {
            amountDueText.setText(decimalFormat.format(Double.parseDouble(amountDueText.getText().toString())));
        } catch (Exception e) {

        }
    }

}