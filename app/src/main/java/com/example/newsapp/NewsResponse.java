package com.example.newsapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("articles")
    private final List<News> vijesti = null;
    @SerializedName("status")
    private final String status = null;

    public String getStatus() {
        return status;
    }

    public List<News> getNews() {
        return vijesti;
    }
}
