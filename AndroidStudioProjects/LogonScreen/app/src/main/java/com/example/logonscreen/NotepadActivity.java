package com.example.logonscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class NotepadActivity extends AppCompatActivity {

    Gson gson = new Gson();
    private String userKey;
    private EditText editTextNotepad;
    private Button buttonSave;
    private User[] users;
    private CharSequence text = "";
    private User currentUser;
    public static String SHARED_PREFERECES_KEY = "shared preferences";
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private TreeMap<String, String> userMap = new TreeMap<>();
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        sharedPreferences = getSharedPreferences(SHARED_PREFERECES_KEY, MODE_PRIVATE);
        recieveData();
        createViews();

    }

    @Override
    public void onBackPressed() {
        writeToFile();
        finish();
    }

    private void recieveData() {
        Intent intent = getIntent();
        userKey = intent.getStringExtra(MainActivity.USER_ID_KEY);

        currentUser = new User("", userKey);
        loadData(sharedPreferences, userKey);

    }

    public void createViews() {
        editTextNotepad = findViewById(R.id.editTextNotepad);
        editTextNotepad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextNotepad.setText(currentUser.getText());

        buttonSave = findViewById(R.id.buttonSaveWork);
        buttonSave.setOnClickListener(e -> writeToFile());

    }
    private void writeToFile() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        currentUser.setText(editTextNotepad.getText().toString());
        String userInformation = editTextNotepad.getText().toString();
        currentUser.setText(userInformation);
        String data = gson.toJson(currentUser);
        editor.putString(firebaseAuth.getUid(), data);
        editor.commit();
        editor.apply();

        userMap.put(currentUser.getId(), currentUser.getText());
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }


    private String loadData(SharedPreferences sharedPreferences, String userID) {
        String string =  sharedPreferences.getString(userID, "");
        if (!string.isEmpty()) {
            currentUser.setText(gson.fromJson(string, User.class).getText());
        } else {
            currentUser.setText("");

        }
        return string;
    }
}
