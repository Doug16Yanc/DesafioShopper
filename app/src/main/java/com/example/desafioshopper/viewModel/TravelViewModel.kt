package com.example.desafioshopper.viewModel

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioshopper.model.Travel
import com.example.desafioshopper.response.RidesResponse
import com.example.desafioshopper.response.TravelResponse
import kotlinx.coroutines.launch

class TravelViewModel() : ViewModel(){
    private val _travels = MutableLiveData<List<Travel>>()
    val travels : LiveData<List<Travel>> = _travels
    private var _travelResponse = mutableStateOf<TravelResponse?>(null)
    var travelResponse : State<TravelResponse?> = _travelResponse
    private val error = mutableStateOf<String?>(null)

     /*fun requestTravel() {
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
    } */
     @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
     fun getRideEstimate(customer_id : String, origin : String, destination : String) {
        viewModelScope.launch {
            try {
                val response: TravelResponse = DriverViewModel().getRideEstimate(customer_id, origin, destination)
                _travelResponse.value = response
            } catch (e: HttpException) {
                Log.e("API Error", (e ?: "Unknown error").toString())
            }
        }
    }

    suspend fun getTravels(customer_id: String, id: String): RidesResponse {
        return try {
            val response = DriverViewModel().apiService.getTravels(customer_id, id)
            response
        } catch (e : Exception) {
            RidesResponse(null, null)
        }
    }
}