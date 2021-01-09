package com.example.newsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //API KEY: 4fc5eb2ae4ca425a8804fd5306df7643
    public static ViewPager viewPager;
    public static TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vpPager);
        tabLayout = findViewById(R.id.tablayout);

        MyPageAdapter viewPagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("postavke", MODE_PRIVATE);
        boolean vice = sharedPreferences.getBoolean("vice", true);
        if(vice) {
            viewPagerAdapter.addFragment(new fnews(0), "Vice News");
        }
        boolean nweek = sharedPreferences.getBoolean("nweek", true);
        if(nweek) {
            viewPagerAdapter.addFragment(new fnews(1), "News Week");
        }
        boolean bbc = sharedPreferences.getBoolean("bbc", true);
        if(bbc) {
            viewPagerAdapter.addFragment(new fnews(2), "BBC News");
        }
        if(viewPagerAdapter.getCount() == 0)
        {
            viewPagerAdapter.addFragment(new fnews(0), "Vice News");
        }
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        ActionBar bar = getSupportActionBar();
        bar.setTitle("News list");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("blue")));
    }
}