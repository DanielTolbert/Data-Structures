package com.example.logonscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button signup;
    private Button login;
    private FirebaseAuth firebaseAuth;

    public String USER_ID_KEY = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
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
                    validateAccountExistence(username.getText().toString(), password.getText().toString());
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
        return !username.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }

    private void validateAccountExistence(String userame, String password) {
        firebaseAuth.signInWithEmailAndPassword(userame, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    Toast.makeText(getBaseContext(), "Welcome!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), notepadActivity.class);
                    intent.putExtra( USER_ID_KEY, firebaseAuth.getCurrentUser().getUid() );
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Bro who are you", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createAccountActivity() {
        Intent intent = new Intent(getBaseContext(), SignupActivity.class);
        startActivity(intent);
    }
}
