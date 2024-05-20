package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    val invoiceID: Int,
    val subTotal: Float,
    val date: String,
    val userID: Int
)



