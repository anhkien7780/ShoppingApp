package android.kien.shoppingapp.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.kien.shoppingapp.models.AvatarImage
import android.kien.shoppingapp.network.AvatarImageApi
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.InputStream



class AvatarImageViewModel : ViewModel() {
    var avatarImage by mutableStateOf<AvatarImage?>(null)
    fun getAvatarImage(username: String) {
        viewModelScope.launch {
            try {
                val avatarImage = AvatarImageApi.retrofitService.getAvatar(username)
                this@AvatarImageViewModel.avatarImage = avatarImage
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}