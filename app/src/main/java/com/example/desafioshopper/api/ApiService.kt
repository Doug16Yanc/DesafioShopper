package com.example.desafioshopper.api

import com.example.desafioshopper.response.Confirm
import com.example.desafioshopper.response.CustomerRideResponse
import com.example.desafioshopper.request.RideEstimate
import com.example.desafioshopper.response.RidesResponse
import com.example.desafioshopper.response.TravelResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {


    @Headers("Content-Type: application/json")
    @POST("/ride/estimate")
    suspend fun getRideEstimate(@Body request: RideEstimate) : TravelResponse

    @PATCH("/ride/confirm")
    suspend fun confirmTravel(@Body confirm : Confirm) : CustomerRideResponse

    @GET("/ride/{customer_id}/?driver_id={id}")
    suspend fun getTravels(@Body travel: String, id: String) : RidesResponse
}

