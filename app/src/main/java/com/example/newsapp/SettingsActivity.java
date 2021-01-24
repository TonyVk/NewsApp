package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity implements Callback<SourcesResponse> {
    List<Sources> portali = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String apiKey = "4fc5eb2ae4ca425a8804fd5306df7643";
        sharedPreferences = getApplicationContext().getSharedPreferences("postavke",MODE_PRIVATE);
        ApiManager.getInstance().service().getSources(apiKey).enqueue(this);
    }

    @Override
    public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
        if(response.body() != null && response.body().getStatus().equals("ok")){
            SourcesResponse sourcesResponse = response.body();
            portali = sourcesResponse.getSources();
            LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final LinearLayout ll = findViewById(R.id.lLayout);
            SwitchMaterial prvi = null;
            boolean BaremJedan = false;
            for (int i=0; i<portali.size(); i++) {
                SwitchMaterial cb = new SwitchMaterial(this);
                cb.setText(portali.get(i).getName());
                cb.setChecked(false);
                cb.setTag(portali.get(i).getName());
                cb.setLayoutParams(paramsButton);
                if(i == 0){
                    prvi = cb;
                }
                ll.addView(cb);
                if(!sharedPreferences.contains(portali.get(i).getName())){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(portali.get(i).getName(), true);
                    editor.apply();
                    cb.setChecked(true);
                    BaremJedan = true;
                }
                else
                {
                    boolean vrijednost = sharedPreferences.getBoolean(portali.get(i).getName(), false);
                    cb.setChecked(vrijednost);
                    if(vrijednost){
                        BaremJedan = true;
                    }
                }
            }
            if(!BaremJedan){
                prvi.setChecked(true);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(portali.get(0).getName(), true);
                editor.apply();
            }
        }
    }

    @Override
    public void onFailure(Call<SourcesResponse> call, Throwable t) {

    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.bSpremi) {
            final LinearLayout ll = findViewById(R.id.lLayout);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (int i=0; i<portali.size(); i++) {
                SwitchMaterial isChecked = ll.findViewWithTag(portali.get(i).getName());
                editor.putBoolean(portali.get(i).getName(), isChecked.isChecked());
            }
            editor.apply();
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}