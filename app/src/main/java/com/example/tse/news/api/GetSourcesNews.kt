package com.example.tse.news.api

import android.util.Log
import com.example.tse.news.model.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "GetSourcesNews"

fun getSourcesNews(
        service: NewsService,
        apiKey: String,
        onSuccess: (source: List<Source>) -> Unit,
        onError: (error: String) -> Unit){

    Log.e(TAG, "Start")

    service.getSourcesNews(apiKey).enqueue(
            object : Callback<SourcesNewsResponse> {
                override fun onFailure(call: Call<SourcesNewsResponse>?, t: Throwable?) {
                    Log.e(TAG, "Error en obtener los datos")
                    onError(t?.message ?: "unknown error")
                }

                override fun onResponse(call: Call<SourcesNewsResponse>?, response: Response<SourcesNewsResponse>?) {
                   Log.e(TAG, "La respuesta es $response")
                    if (response!!.isSuccessful){
                        val sources = response.body()?.sources ?: emptyList()
                        onSuccess(sources)
                    }else{
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }

            }
    )

}