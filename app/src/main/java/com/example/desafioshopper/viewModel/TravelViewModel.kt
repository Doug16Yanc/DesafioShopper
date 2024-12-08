package com.example.desafioshopper.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioshopper.api.Retrofit
import com.example.desafioshopper.model.Travel

class TravelViewModel() : ViewModel(){

    private val _travels = MutableLiveData<MutableList<Travel>> (mutableListOf())
    val travels : LiveData<MutableList<Travel>> = _travels

    private fun requestTravel(origin : String, destination : String) {
    }
}