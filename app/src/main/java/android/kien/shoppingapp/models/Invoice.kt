package android.kien.shoppingapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    val subTotal: Float,
    val date: String,
    val address: String,
    val paid: Boolean,
    val listProductID: List<Int>,
    val username: String
)



