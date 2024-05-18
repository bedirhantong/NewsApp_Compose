package com.example.news_compose

object MockData {
    //Todo 3: create a news list
    val topNewsList = listOf<NewsData>(
        NewsData(
            1,
            author = "Bedirhan Tong",
            title = "Appcent Internship",
            description = "Appcent Testcase",
            publishedAt = "2024-05-18T12:22:32Z"
        ),
        NewsData(
            2,
            author = "Bedirhan Tong",
            title = "Appcent Internship 2",
            description = "Appcent Testcase",
            publishedAt = "2024-05-18T12:22:32Z"
        ),
        NewsData(
            3,
            author = "Bedirhan Tong",
            title = "Appcent Internship 3",
            description = "Appcent Testcase",
            publishedAt = "2024-05-18T12:22:32Z"
        ),
        NewsData(
            4,
            author = "Bedirhan Tong",
            title = "Appcent Internship 4",
            description = "Appcent Testcase",
            publishedAt = "2024-05-18T12:22:32Z"
        ),
        NewsData(
            5,
            author = "Bedirhan Tong",
            title = "Appcent Internship 5",
            description = "Appcent Testcase",
            publishedAt = "2024-05-18T12:22:32Z"
        ),
    )
    fun getNews(newsId: Int?): NewsData {
        return topNewsList.first { it.id == newsId }
    }
}