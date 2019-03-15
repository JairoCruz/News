package com.example.tse.news.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * News API communication setup via Retrofit
 * */
interface NewsService {

    /**
     *
     * API Endpoint EVERYTHING
    * Get News by country
    * */
    @GET("everything")
   fun  getNewsByCountry(
            // @Query("q") topic: String,
            @Query("apiKey") apiKey: String,
            @Query("sources") sources: String,
            @Query("language") language: String,
            @Query("page") page: Int,
            @Query("pageSize") pageSize: Int
    ): Call<NewsByTopicResponse>

    /**
     *
     * API EndPoint SOURCES
    *  Get the Sources News
    * */
    @GET("sources")
    fun getSourcesNews(
            @Query("apiKey") apiKey: String
    ): Call<SourcesNewsResponse>



    companion object {

        private const val BASE_URL = "https://newsapi.org/v2/"

        fun create(): NewsService {

            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NewsService::class.java)
        }
    }


}