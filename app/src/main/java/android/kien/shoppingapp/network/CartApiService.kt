package android.kien.shoppingapp.network

import android.kien.shoppingapp.models.Cart
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApiService{
    @GET("/cart/{username}")
    suspend fun getCart(@Path("username") username: String): Cart
    @GET("/cart/add/{username}")
    suspend fun addNewCart(@Path("username") username: String): Cart
    @POST("/cart/add/{cartID}/{productID}")
    suspend fun addToCart(
        @Path("cartID") cartID: Int,
        @Path("productID") productId: Int
    )
    @POST("/cart/update/{cartID}/{productID}/{quantity}")
    suspend fun updateCartItemQuantity(
        @Path("cartID") cartID: Int,
        @Path("productID") productID: Int,
        @Path("quantity") quantity: Int
    ): Cart
    @DELETE("/cart/delete/{cartID}")
    suspend fun deleteCart(@Path("cartID") cartID: Int): Cart
    @DELETE("/cart/delete/{cartID}/{productID}")
    suspend fun deleteCartItem(@Path("cartID") cartID: Int, @Path("productID") productID: Int): Boolean

}

object CartApi{
    val retrofitService: CartApiService by lazy {
        retrofit.create(CartApiService::class.java)
    }
}