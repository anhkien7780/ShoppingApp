package android.kien.shoppingapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val cartID: Int,
    val listCartItem: List<CartItem>
)


@Serializable
data class CartItem(
    val productID: Int,
    var quantity: Int
)