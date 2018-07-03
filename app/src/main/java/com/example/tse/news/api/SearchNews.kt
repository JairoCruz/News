package com.example.tse.news.api

import android.util.Log
import com.example.tse.news.model.Article
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


private const val TAG = "SearchNews"


fun searchNews(service: NewsService,
               query: String,
               apiKey: String,
               onSuccess: (article: List<Article>) -> Unit,
               onError: (error: String) -> Unit){

    Log.e(TAG, "Query: $query")

    service.getNewsByCountry(query, apiKey).enqueue(
            object : Callback<NewsByCountryResponse> {
                override fun onFailure(call: Call<NewsByCountryResponse>?, t: Throwable?) {
                    Log.e(TAG, "Error en obtener los datos")
                    onError(t?.message ?: "unknown error")
                }

                override fun onResponse(call: Call<NewsByCountryResponse>?, response: Response<NewsByCountryResponse>?) {
                    Log.e(TAG, "La respuesta es $response")
                    if (response!!.isSuccessful){
                        val articles = response.body()?.articles ?: emptyList()
                        onSuccess(articles)
                    }else{
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }

            })

}