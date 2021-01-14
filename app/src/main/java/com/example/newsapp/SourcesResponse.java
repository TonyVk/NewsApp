package com.example.newsapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourcesResponse {
    @SerializedName("sources")
    private List<Sources> portali = null;
    @SerializedName("status")
    private String status;

    public String getStatus() { return status; }

    public List<Sources> getSources() {
        return portali;
    }
}
