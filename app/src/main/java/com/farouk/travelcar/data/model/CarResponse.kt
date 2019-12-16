package com.farouk.travelcar.data.model

import java.io.Serializable

data class CarResponse(
    val equipments: List<String>,
    val make: String,
    val model: String,
    val picture: String,
    val year: Int
) : Serializable