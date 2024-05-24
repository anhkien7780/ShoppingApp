package android.kien.shoppingapp.network

import com.google.android.engage.common.datamodel.Image
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AvatarImageApiService{
    @Multipart
    @POST("/avatar/add/{userID}")
    suspend fun addImage(@Part image: MultipartBody.Part, @Path("userID") userID: Int)
    @GET("avatar/get/{userID}")
    suspend fun getAvatar(@Path("userID") userID: String): Image
}

object AvatarImageApi{
    val retrofitService: AvatarImageApiService by lazy {
        retrofit.create(AvatarImageApiService::class.java)
    }
}