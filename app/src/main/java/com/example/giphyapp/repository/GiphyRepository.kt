package com.example.giphyapp.repository

import com.example.giphyapp.api.RetrofitInstance

class GiphyRepository {
    suspend fun getGiphy() = RetrofitInstance.api.getGiphy()
}