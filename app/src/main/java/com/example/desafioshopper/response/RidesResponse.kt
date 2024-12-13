package com.example.desafioshopper.response

import com.example.desafioshopper.model.Travel

data class RidesResponse(
    val customer_id : String? = "",
    val travels : List<Travel>?
)