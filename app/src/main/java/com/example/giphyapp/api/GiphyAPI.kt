package com.example.giphyapp.api

import com.example.giphyapp.models.GiphyResponse
import com.example.giphyapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {

    @GET("v1/gifs/trending")
    suspend fun getGiphy(
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<GiphyResponse>
}