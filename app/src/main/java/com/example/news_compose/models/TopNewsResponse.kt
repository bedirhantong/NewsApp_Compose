package com.example.news_compose.models

data class TopNewsResponse(
    val status : String? = null,
    val totalResults : Int? = null,
    val articles : List<TopNewsArticle>? = null)
