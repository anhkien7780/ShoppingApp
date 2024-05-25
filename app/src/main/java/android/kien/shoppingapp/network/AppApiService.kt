package android.kien.shoppingapp.network

import android.kien.shoppingapp.models.Account
import com.example.models.Product
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "http://192.168.1.12:8080"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AccountApiService{
    @POST("/accounts/login")
    suspend fun login(@Body account: Account)
    @POST("/accounts/register")
    suspend fun register(@Body account: Account)

}
object AccountApi{
    val retrofitService: AccountApiService by lazy {
        retrofit.create(AccountApiService::class.java)
    }
}