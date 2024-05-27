package android.kien.shoppingapp.network

import android.kien.shoppingapp.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService{
    @GET("/users/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @POST("/users/add")
    suspend fun addNewUser(@Body user: User)
    @POST("/users/change")
    suspend fun updateUser(@Body user: User)
}

object UserApi{
    val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}