package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        WebView editWeb = findViewById(R.id.web);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        editWeb.getSettings().setLoadsImagesAutomatically(true);
        editWeb.getSettings().setJavaScriptEnabled(true);
        editWeb.getSettings().setAllowFileAccess(true);
        editWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        editWeb.loadUrl(url);
    }
}