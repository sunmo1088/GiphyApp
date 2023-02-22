package com.example.giphyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphyapp.repository.GiphyRepository

class GiphyViewModelProviderFactory(
    private val giphyRepository: GiphyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GiphyViewModel(giphyRepository) as T
    }
}