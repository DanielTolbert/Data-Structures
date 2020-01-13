package com.example.logonscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextRe_password;
    private Button buttonCreateAccount;
    private Button buttonLoginPage;

    public SignupActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        createViews();
    }

    private void createViews() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRe_password = findViewById(R.id.editTextReenterPassword);

        buttonCreateAccount = findViewById(R.id.buttonMakeAccount);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePassword()) {
                    createAccount(editTextUsername.getText().toString(), editTextPassword.getText().toString());
                }
            }
        });

        buttonLoginPage = findViewById(R.id.buttonLoginPage);
        buttonLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validatePassword() {
        boolean matchingPasswords = editTextPassword.getText().toString().equals( editTextUsername.getText().toString() );
        return matchingPasswords;
    }

    private void createAccount(String user, String pass) {

    }


}
