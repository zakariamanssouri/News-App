package com.example.newsapifinal.ApiServices;

import com.example.newsapifinal.Models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    String API_KEY = "797ad36f3803451e9993e6b1cfc6a5c2";
    String Base_URL = "https://newsapi.org/v2/";

    @GET("everything")
    Call<News> getNews(
            @Query("q") String key,
            @Query("from") String date,
            @Query("apiKey") String apikey);
}
