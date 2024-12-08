package com.example.desafioshopper.api

import com.example.desafioshopper.model.Driver
import com.example.desafioshopper.model.Travel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDateTime

interface ApiService {
    @GET("travels")
    fun getTravels(
        @Query("dateTime") dateTime : LocalDateTime,
        @Query("name") name : String,
        @Query("origin") origin : String,
        @Query("destination") destination : String,
        @Query("distance") distance : Double,
        @Query("value") value : Double
    ) : Call<List<Travel>>

    @GET("drivers")
    fun getDrivers(
        @Query("name") name : String,
        @Query("description") description : String,
        @Query("vehicle") vehicle : String,
        @Query("rate") rate : Double,
        @Query("value") value : Double
    ) : Call<List<Driver>>
}