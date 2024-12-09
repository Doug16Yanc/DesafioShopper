package com.example.desafioshopper.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioshopper.api.Retrofit
import com.example.desafioshopper.model.Driver
import kotlinx.coroutines.launch

class DriverViewModel : ViewModel() {

    private val _drivers = MutableLiveData<List<Driver>>()
    val drivers : LiveData<List<Driver>> = _drivers

    fun getDrivers() {
        viewModelScope.launch {
            try {
                val response = Retrofit.api.getDrivers()
                _drivers.value = response
            } catch (e : Exception) {
                e.printStackTrace()
                setOf(Toast.makeText(this, "Lista vazia.", Toast.LENGTH_SHORT).show())
                _drivers.value = emptyList()
            }
        }
    }
}