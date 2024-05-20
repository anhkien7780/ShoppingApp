package com.example.models


import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val productID: Int,
    val productName: String,
    val price: Float,
    val imgSrc: String,
    val description: String
)


