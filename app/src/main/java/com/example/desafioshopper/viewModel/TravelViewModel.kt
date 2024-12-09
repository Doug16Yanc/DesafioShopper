package com.example.desafioshopper.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioshopper.api.Retrofit
import com.example.desafioshopper.model.Travel
import kotlinx.coroutines.launch

class TravelViewModel() : ViewModel(){

    private val _travels = MutableLiveData<List<Travel>>()
    val travels : LiveData<List<Travel>> = _travels

     fun requestTravel() {
        viewModelScope.launch {
            try {
                val response = Retrofit.api.getTravels()
                _travels.value = response
            } catch (e : Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Lista vazia", Toast.LENGTH_SHORT).show()
                _travels.value = emptyList()
            }
        }
    }
}