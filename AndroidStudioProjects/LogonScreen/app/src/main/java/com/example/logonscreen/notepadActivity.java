package com.example.logonscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

public class notepadActivity extends AppCompatActivity {

    Gson gson = new Gson();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
    }

    private void recieveData() {

    }

    public void createViews() {

    }
    private void createFile() {

    }
}
