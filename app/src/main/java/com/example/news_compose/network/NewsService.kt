package com.example.news_compose.network

import com.example.news_compose.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getTopArticles(
        @Query("country") country:String,
        @Query("apiKey")apiKey:String): Call<TopNewsResponse>
}