package com.victor.victor.showmap.getPalces

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victor.victor.learningRetrofit.Retrofit.Repostitory.Repository

class PlaceViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PlacesViewModel(repository) as T
    }
}