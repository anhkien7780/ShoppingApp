package android.kien.shoppingapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val username: String,
    val password: String,
)