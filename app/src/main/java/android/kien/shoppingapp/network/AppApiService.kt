package android.kien.shoppingapp.network

import android.kien.shoppingapp.models.Account
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://one-definitely-kingfish.ngrok-free.app"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AccountApiService{
    @POST("/accounts/login")
    suspend fun login(@Body account: Account)
    @POST("accounts/change-password/{oldPassword}")
    suspend fun changePassword(@Path("oldPassword") oldPassword: String, @Body account: Account)
    @POST("/accounts/register")
    suspend fun register(@Body account: Account)
}
object AccountApi{
    val retrofitService: AccountApiService by lazy {
        retrofit.create(AccountApiService::class.java)
    }
}