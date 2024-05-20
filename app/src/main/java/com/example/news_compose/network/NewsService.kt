package com.example.news_compose.network

import com.example.news_compose.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getTopArticles(
        @Query("country") country:String):
            TopNewsResponse

    @GET("everything")
    suspend fun searchArticles(@Query("q") country:String):TopNewsResponse


}