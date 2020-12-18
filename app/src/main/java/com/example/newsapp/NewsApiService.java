package com.example.newsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("everything")
    Call<NewsResponse> getNews (@Query("domains") String source, @Query("page") int page, @Query("apiKey") String apiKey);
}
