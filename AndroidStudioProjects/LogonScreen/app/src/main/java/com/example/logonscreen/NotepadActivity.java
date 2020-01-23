package com.example.logonscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NotepadActivity extends AppCompatActivity {

    Gson gson = new Gson();
    private String userKey;
    private EditText editTextNotepad;
    private CharSequence text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        createViews();
    }

    private void recieveData() {
        Intent intent = getIntent();
        userKey = intent.getStringExtra(MainActivity.USER_ID_KEY);
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
    }
    private void writeToFile() {

        File userFile = new File(userKey + ".json");
        if(!userFile.exists()) {
            try {
                userFile.createNewFile();
                FileInputStream fileInputStream = openFileInput(userFile.getName());
                int bytes = fileInputStream.available();
                byte[] buffer = new byte[bytes];
                fileInputStream.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
