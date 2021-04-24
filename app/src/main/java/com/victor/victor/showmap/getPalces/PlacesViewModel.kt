package com.victor.victor.showmap.getPalces

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.victor.learningRetrofit.Retrofit.Repostitory.Repository
import com.victor.victor.learningRetrofit.Retrofit.model.Post
import kotlinx.coroutines.launch
import retrofit2.Response

class PlacesViewModel(private  val repository: Repository) :ViewModel() {

    val myResponse:MutableLiveData<Response<Post>> = MutableLiveData();

    fun getPost(){
        viewModelScope.launch {
            val respond:Response<Post> = repository.getPost();
            myResponse.value = respond
        }
    }

}