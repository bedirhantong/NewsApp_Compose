package com.example.news_compose

data class NewsData(val id:Int, val image:Int = R.drawable.appcent_1, val author:String, val title:String, val description:String,val publishedAt:String)