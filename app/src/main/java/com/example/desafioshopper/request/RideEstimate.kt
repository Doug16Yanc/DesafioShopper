package com.example.desafioshopper.request

data class RideEstimate(
    val customer_id : String,
    val origin : String,
    val destination : String
)