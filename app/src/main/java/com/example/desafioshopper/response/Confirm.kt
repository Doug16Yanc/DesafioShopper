package com.example.desafioshopper.response

import com.example.desafioshopper.model.Driver

data class Confirm(
    val customer_id : String? = "",
    val origin : String? = "",
    val destination : String? = "",
    val distance : Double?= 0.0,
    val duration : String? = "",
    val id : Int? = 0,
    val name : String? = "",
    val value : Double? = 0.0
)
