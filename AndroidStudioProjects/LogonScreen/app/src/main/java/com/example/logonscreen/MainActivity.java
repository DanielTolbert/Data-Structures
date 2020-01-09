package com.example.logonscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private Button signup;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createViews();
    }

    private void createViews() {
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);

        login = findViewById(R.id.buttonLogIn);
        signup = findViewById(R.id.buttonMakeAccount);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( validatePassword() ){
                    validateAccountExistence();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccountActivity();
            }
        });
    }

    private boolean validatePassword() {
        return false;
    }

    private void validateAccountExistence() {

    }

    private void createAccountActivity() {
        Intent intent = new Intent(getBaseContext(), SignupActivity.class);
        startActivity(intent);
    }
}
