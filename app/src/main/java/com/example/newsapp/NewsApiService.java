package com.example.newsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("everything")
    Call<NewsResponse> getNews (@Query("sources") String source, @Query("page") int page, @Query("apiKey") String apiKey);
    @GET("sources")
    Call<SourcesResponse> getSources (@Query("apiKey") String apiKey);
}
