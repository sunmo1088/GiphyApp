package com.example.giphyapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyapp.models.GiphyResponse
import com.example.giphyapp.repository.GiphyRepository
import com.example.giphyapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GiphyViewModel(
    private val giphyRepository: GiphyRepository
) : ViewModel() {

    val giphy: MutableLiveData<Resource<GiphyResponse>> = MutableLiveData()

    init {
        getGiphy()
    }

    private fun getGiphy() = viewModelScope.launch {
        giphy.postValue(Resource.Loading())
        val response = giphyRepository.getGiphy()
        giphy.postValue(handleGiphyResponse(response))
    }

    private fun handleGiphyResponse(response: Response<GiphyResponse>) : Resource<GiphyResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}