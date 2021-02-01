package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<SourcesResponse> {
    //API KEY: 4fc5eb2ae4ca425a8804fd5306df7643
    public static ViewPager viewPager;
    public static TabLayout tabLayout;
    String apiKey = "4fc5eb2ae4ca425a8804fd5306df7643";
    SharedPreferences sharedPreferences;
    MyPageAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vpPager);
        tabLayout = findViewById(R.id.tablayout);

        viewPagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        sharedPreferences = getApplicationContext().getSharedPreferences("postavke", MODE_PRIVATE);
        ApiManager.getInstance().service().getSources(apiKey).enqueue(this);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Vijesti");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("blue")));
    }

    @Override
    public void onResponse(@NonNull Call<SourcesResponse> call, Response<SourcesResponse> response) {
        if(response.body() != null && response.body().getStatus().equals("ok")){
            SourcesResponse sourcesResponse = response.body();
            List<Sources> portali = sourcesResponse.getSources();
            boolean BaremJedan = false;
            for (int i=0; i<portali.size(); i++) {
                if(!sharedPreferences.contains(portali.get(i).getName())){
                    viewPagerAdapter.addFragment(new fnews(portali.get(i).getId()), portali.get(i).getName());
                    BaremJedan = true;
                }
                else if(sharedPreferences.getBoolean(portali.get(i).getName(), false))
                {
                    viewPagerAdapter.addFragment(new fnews(portali.get(i).getId()), portali.get(i).getName());
                    BaremJedan = true;
                }
            }
            if(!BaremJedan) {
                viewPagerAdapter.addFragment(new fnews(portali.get(0).getId()), portali.get(0).getName());
            }
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(@NonNull Call<SourcesResponse> call, @NonNull Throwable t) {

    }
}