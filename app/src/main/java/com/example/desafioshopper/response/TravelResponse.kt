package com.example.desafioshopper.response

import com.example.desafioshopper.model.Driver

data class TravelResponse(
    val origin: Location,
    val destination: Location,
    val distance: Double,
    val duration: String,
    val options: List<Driver>,
    val value: Double,
    val routeResponse: Any?
)

data class Location(
    val latitude: Double,
    val longitude: Double
)