package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Button button = findViewById(R.id.bSpremi);
        SwitchMaterial sw = (SwitchMaterial) findViewById(R.id.switch1);
        SwitchMaterial sw2 = (SwitchMaterial) findViewById(R.id.switch2);
        SwitchMaterial sw3 = (SwitchMaterial) findViewById(R.id.switch3);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("postavke",MODE_PRIVATE);
        boolean vice = sharedPreferences.getBoolean("vice", false);
        boolean nweek = sharedPreferences.getBoolean("nweek", false);
        boolean bbc = sharedPreferences.getBoolean("bbc", false);
        if(vice) {
            sw.setChecked(true);
        }
        if(nweek) {
            sw2.setChecked(true);
        }
        if(bbc) {
            sw3.setChecked(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean vice = ((SwitchMaterial) findViewById(R.id.switch1)).isChecked();
                boolean nweek = ((SwitchMaterial) findViewById(R.id.switch2)).isChecked();
                boolean bbc = ((SwitchMaterial) findViewById(R.id.switch3)).isChecked();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("vice", vice);
                editor.putBoolean("nweek", nweek);
                editor.putBoolean("bbc", bbc);
                editor.apply();
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });
    }
}