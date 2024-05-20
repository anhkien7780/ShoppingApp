package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val addressID: Int,
    val userID: Int,
    val address: String
)

