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

sealed class AvatarImageUiState {
    object Idle : AvatarImageUiState()
    object Success : AvatarImageUiState()
    object Error : AvatarImageUiState()
    object Loading : AvatarImageUiState()
}

class AvatarImageViewModel : ViewModel() {
    var avatarImage by mutableStateOf<AvatarImage?>(null)
        private set
    var avatarImageUiState: AvatarImageUiState by mutableStateOf(AvatarImageUiState.Idle)
        private set

    fun uploadAvatarImage(uri: Uri?, context: Context, username: String) {
        viewModelScope.launch {
            try {
                avatarImageUiState = AvatarImageUiState.Loading
                val contentResolver: ContentResolver = context.contentResolver
                val inputStream: InputStream? = uri?.let { it ->
                    contentResolver.openInputStream(it)
                }
                val byteArrayOutputStream = ByteArrayOutputStream()
                inputStream?.copyTo(byteArrayOutputStream)
                val byteArray = byteArrayOutputStream.toByteArray()
                val imageMediaType = "image/png".toMediaType()
                val imageRequestBody = byteArray.toRequestBody(imageMediaType)
                val imagePart = MultipartBody.Part.createFormData("image", "${username}_avatar.png", imageRequestBody)
                AvatarImageApi.retrofitService.addImage(imagePart, username)
                avatarImageUiState = AvatarImageUiState.Success
            } catch (e: Exception) {
                avatarImageUiState = AvatarImageUiState.Error
                e.printStackTrace()
            } finally {
                avatarImageUiState = AvatarImageUiState.Idle
            }
        }
    }
}