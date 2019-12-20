package com.example.agreementproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    Button buttonTerms;

    String code;

    boolean agreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recieveData();
        makeButtons();
    }

    private void makeButtons() {
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonTerms = findViewById(R.id.buttonTerms);

        if (clickedAgree()) {
            buttonLogin.setEnabled(true);
        } else {
            buttonLogin.setEnabled(false);
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAgreement()) {
                        successToast();
                } else {
                    failureToast();
                }
            }
        });

        buttonTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TermsActivity.class);
                startActivity(intent);
            }
        });
    }

    public String getCode() {
        return  " " + code + " ";
    }

    public void setCode(String code) {
        this.code = code;
    }

    private void recieveData() {
        Intent intent = getIntent();
        code = intent.getStringExtra(TermsActivity.CODE_KEY);
        agreed = intent.getBooleanExtra(TermsActivity.AGREE_KEY, false);
    }

    private boolean checkAgreement() {
        return code.equals("sialoquent");
    }

    private boolean clickedAgree() {
        return agreed;
    }

    private void successToast() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.successOne) +
                getCode() +
                getResources().getString(R.string.successTwo), Toast.LENGTH_LONG).show();
    }

    private void failureToast() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.failOne) +
                getCode() +
                getResources().getString(R.string.failTwo), Toast.LENGTH_LONG).show();
    }
}
