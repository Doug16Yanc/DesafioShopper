package com.example.desafioshopper.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioshopper.api.ApiService
import com.example.desafioshopper.response.Location
import com.example.desafioshopper.request.RideEstimate
import com.example.desafioshopper.response.Confirm
import com.example.desafioshopper.response.TravelResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DriverViewModel : ViewModel() {
    private val BASE_URL = "https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws"
    private val _value = MutableLiveData<String>()
    val value: LiveData<String> get() = _value

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    suspend fun getRideEstimate(customer_id : String, origin: String, destination: String): TravelResponse {
        return try {
            val request = RideEstimate(
                customer_id = customer_id,
                origin = origin,
                destination = destination
            )
            apiService.getRideEstimate(request)
        } catch (e: Exception) {
            e.printStackTrace()
            TravelResponse(
                origin = Location(0.0, 0.0),
                destination = Location(0.0, 0.0),
                distance = 0.0,
                duration = "Error",
                options = emptyList(),
                value = 0.0,
                routeResponse = null
            )
        }
    }

    suspend fun confirmTravel(confirm: Confirm) : Boolean{
       return try {
           val response = apiService.confirmTravel(confirm)
           response.success
       } catch (e : Exception) {
           false
       }
    }
}
