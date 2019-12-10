package com.example.listviewquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textViewPlayers;
    EditText editTextName;
    ListView listViewPLayers;
    Button buttonAddPlayers;
    boolean maxLen = false;

    ArrayList<String> playerArrayList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMiscellaneousViews();
    }

    private void createMiscellaneousViews() {
        textViewPlayers = findViewById(R.id.textViewPlayers);
        editTextName = findViewById(R.id.editTextName);
        buttonAddPlayers = findViewById(R.id.buttonAddPlayers);

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextName.getText().toString().isEmpty() || maxLen) {
                    buttonAddPlayers.setEnabled(false);
                } else {
                    buttonAddPlayers.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonAddPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPlayer();
                editTextName.setText("");
                buttonAddPlayers.setEnabled(false);
                maxLen = (playerArrayList.size() >= 8);
                if (maxLen) {
                    Toast.makeText(getBaseContext(), R.string.full, Toast.LENGTH_LONG).show();
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        listViewPLayers = findViewById(R.id.listViewPlayers);
    }

    private void addNewPlayer() {
        playerArrayList.add(editTextName.getText().toString());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  R.layout.my_list_item, playerArrayList);
        listViewPLayers.setAdapter(arrayAdapter);
    }
}
