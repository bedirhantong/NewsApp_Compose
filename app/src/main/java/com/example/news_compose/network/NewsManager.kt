package com.example.news_compose.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.example.news_compose.models.TopNewsResponse
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager(private val service: NewsService) {
    private val _newsResponse =
        mutableStateOf(TopNewsResponse())


     suspend fun getArticles(country :String):TopNewsResponse = withContext(
        Dispatchers.IO
    ){
        service.getTopArticles(country = country)
    }
    suspend fun getSearchedArticles(query: String):TopNewsResponse = withContext(Dispatchers.IO){
        service.searchArticles(query)
//        val service = Api.retrofitService.getTopArticlesByQuery(query)
//        service.enqueue(object :Callback<TopNewsResponse>{
//            override fun onResponse(call: Call<TopNewsResponse>, response: Response<TopNewsResponse>) {
//                if (response.isSuccessful){
//                    _searchedNewsResponse.value = response.body()!!
//                    Log.d("search","${_searchedNewsResponse.value}")
//                }else{
//                    Log.d("search","${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
//                Log.d("searcherror","${t.printStackTrace()}")
//            }
//
//        })
    }
}