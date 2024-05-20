package com.example.news_compose.data

import com.example.news_compose.network.NewsManager

class Repository (private val manager:NewsManager) {
    suspend fun getArticles () = manager.getArticles("us")
    suspend fun getSearchedArticles(query:String) = manager.getSearchedArticles(query = query)

}