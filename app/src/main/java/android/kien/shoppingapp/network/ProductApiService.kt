package android.kien.shoppingapp.network

import com.example.models.Product
import retrofit2.http.GET

interface ProductApiService{
    @GET("/products")
    suspend fun getProducts(): List<Product>
}

object ProductApi{
    val retrofitService: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }
}