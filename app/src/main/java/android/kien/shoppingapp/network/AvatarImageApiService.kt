package android.kien.shoppingapp.network

import android.kien.shoppingapp.models.AvatarImage
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AvatarImageApiService{
    @Multipart
    @POST("/avatar/add/{username}")
    suspend fun addImage(@Part image: MultipartBody.Part, @Path("username") username: String)
    @GET("avatar/get/{userID}")
    suspend fun getAvatar(@Path("username") username: String): AvatarImage
}

object AvatarImageApi{
    val retrofitService: AvatarImageApiService by lazy {
        retrofit.create(AvatarImageApiService::class.java)
    }
}