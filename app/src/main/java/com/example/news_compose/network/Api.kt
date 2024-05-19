package com.example.news_compose.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {
    const val API_KEY = "bf48f43561864e76ba4db2dad7ffac96"
    private const val BASE_URL = "https://newsapi.org/v2/"

    // initialize moshi
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // to be able to see what is going on around the request we have been sending
    private val logging = HttpLoggingInterceptor()

    // passing API through header
    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor{
                    chain ->
                val builder = chain.request().newBuilder()
                // request parameter on header
                builder.header("X-Api-key",API_KEY)
                return@Interceptor chain.proceed(builder.build())
            }
        )

        // to be able to see details search for okhttp on logcat
        logging.level = HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(logging)
    }.build()


    // initialize retrofit
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    val retrofitService : NewsService by lazy{
        retrofit.create(NewsService::class.java)
    }
}