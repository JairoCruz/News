package com.example.tse.news.api

import android.util.Log
import com.example.tse.news.model.Article
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


private const val TAG = "SearchNews"


fun searchNews(service: NewsService,
               // topic: String,
               apiKey: String,
               sources: String,
               language: String,
               page: Int,
               pageSize: Int,
               onSuccess: (article: List<Article>) -> Unit,
               onError: (error: String) -> Unit){

   // Log.e(TAG, "Query: $topic")

    service.getNewsByCountry(/* topic, */ apiKey, sources, language, page, pageSize).enqueue(
            object : Callback<NewsByTopicResponse> {
                override fun onFailure(call: Call<NewsByTopicResponse>?, t: Throwable?) {
                    Log.e(TAG, "Error en obtener los datos")
                    onError(t?.message ?: "unknown error")
                }

                override fun onResponse(call: Call<NewsByTopicResponse>?, response: Response<NewsByTopicResponse>?) {
                    Log.e(TAG, "La respuesta es $response")
                    if (response!!.isSuccessful){
                        val articles = response.body()?.articles ?: emptyList()
                        val totalResult = response.body()?.total ?: 0
                        val status = response.body()?.status ?: ""
                        Log.e(TAG, "Total de paginas: $totalResult y Estatus: $status")
                        onSuccess(articles)
                    }else{
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }

            })

}