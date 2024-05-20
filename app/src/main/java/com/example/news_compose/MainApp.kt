package com.example.news_compose

import android.app.Application
import com.example.news_compose.data.Repository
import com.example.news_compose.network.Api
import com.example.news_compose.network.NewsManager

class MainApp :Application() {
    private val manager by lazy {
        NewsManager(Api.retrofitService)
    }

    val repository by lazy {
        Repository(manager = manager)
    }
}