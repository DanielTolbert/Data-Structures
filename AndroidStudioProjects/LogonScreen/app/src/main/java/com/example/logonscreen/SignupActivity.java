package com.example.logonscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextRe_password;
    private TextView textViewPasswordSpecifications;
    private Button buttonCreateAccount;
    private Button buttonLoginPage;
    private Button buttonShowPasswordOne;
    private Button buttonShowPasswordTwo;
    private FirebaseAuth firebaseAuth;
    private Switch switchShowOne;
    private Switch switchShowTwo;

    public SignupActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        createViews();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void createViews() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRe_password = findViewById(R.id.editTextReenterPassword);
        textViewPasswordSpecifications = findViewById(R.id.textViewPasswordSpecifications);


        buttonCreateAccount = findViewById(R.id.buttonMakeAccount);
        buttonCreateAccount.setEnabled(shouldEnableMakeAccount());
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordsMatch()) {
                    if (passwordsMeetSpecifications(editTextPassword.getText().toString())) {
                        createAccount(editTextUsername.getText().toString(), editTextPassword.getText().toString());
                    } else {
                        invalidPassword();
                    }
                } else {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.passwordsNoMatch), Toast.LENGTH_LONG).show();
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

        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonCreateAccount.setEnabled(shouldEnableMakeAccount());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextRe_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonCreateAccount.setEnabled(shouldEnableMakeAccount());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonCreateAccount.setEnabled(shouldEnableMakeAccount());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void invalidPassword() {
        Toast.makeText(this, getResources().getString(R.string.passwordInvalid), Toast.LENGTH_LONG).show();
    }

    private boolean shouldEnableMakeAccount() {
        return !editTextUsername.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty();
    }

    private boolean passwordsMatch() {
        if (editTextPassword.getText().toString().equals( editTextRe_password.getText().toString() )) {
            return true;
        }
        return false;
    }

    private boolean passwordsMeetSpecifications( String password ) {
        return password.matches("(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*\\d+)(?=.*[^!@#$%*]+).{8,16}");
    }

    private void createAccount(String user, String pass) {
        firebaseAuth.createUserWithEmailAndPassword( user, pass ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.accountCreated), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.accountFailed), Toast.LENGTH_LONG).show();
                }
            }
        });
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }


}
