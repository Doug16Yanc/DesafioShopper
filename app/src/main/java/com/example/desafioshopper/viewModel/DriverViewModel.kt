package com.example.desafioshopper.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafioshopper.model.Driver

class DriverViewModel {

    private val _drivers = MutableLiveData<MutableList<Driver>> (mutableListOf())
    val drivers : LiveData<MutableList<Driver>> = _drivers

}