package com.example.desafioshopper.api

import com.example.desafioshopper.model.Driver
import com.example.desafioshopper.model.Travel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDateTime

interface ApiService {
    @GET("travels")
   suspend fun getTravels() : List<Travel>

    @GET("drivers")
    suspend fun getDrivers() : List<Driver>
}