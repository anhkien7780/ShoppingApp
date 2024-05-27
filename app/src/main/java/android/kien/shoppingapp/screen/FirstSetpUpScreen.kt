package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.models.AvatarImage
import android.kien.shoppingapp.models.Date
import android.kien.shoppingapp.models.User
import android.kien.shoppingapp.network.AvatarImageApi
import android.kien.shoppingapp.network.UserApi
import android.kien.shoppingapp.viewmodel.AvatarImageViewModel
import android.kien.shoppingapp.viewmodel.UserViewModel
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstSetupScreen(
    context: Context,
    username: String,
    userViewModel: UserViewModel,
    avatarImageViewModel: AvatarImageViewModel,
    onAddUserInfo: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var name by remember {
        mutableStateOf("")
    }
    var sex by remember {
        mutableStateOf(true)
    }
    var birthDay by remember {
        mutableStateOf(Date(1, 1, 2002).toLocalDate())
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var uri by remember {
        mutableStateOf<Uri?>(null)
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "FIRST SETUP",
                fontFamily = rignteousFont
            )
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            ChangeAvatar(placeholder = R.drawable.default_logo, onUriChange = { uri = it }, "")
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.padding(10.dp))
            MyOutlinedTextFiled(
                value = name,
                onValuedChange = { name = it },
                label = "Full name",
                placeholder = name
            )
            MyOutlinedTextFiled(
                value = phoneNumber,
                onValuedChange = { phoneNumber = it },
                label = "Phone number",
                placeholder = phoneNumber
            )
            Spacer(modifier = Modifier.padding(10.dp))
            GenderSelection(sex = if (sex) "Male" else "Female") { sex = it == "Male" }
            Spacer(modifier = Modifier.padding(10.dp))
            BirthdaySelection(
                birthday = Date(
                    birthDay.dayOfMonth,
                    birthDay.monthValue,
                    birthDay.year
                )
            ) {
                birthDay = it.toLocalDate()
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            try {
                                val user = User(
                                    name = name,
                                    birthDay = birthDay.format(DateTimeFormatter.ofPattern("d/MM/yyyy")),
                                    age = LocalDate.now().year - birthDay.year,
                                    sex = sex,
                                    phoneNumber = phoneNumber,
                                    username = username
                                )
                                userViewModel.user.value = user
                                UserApi.retrofitService.addNewUser(user)
                                val imagePart = uriToMultipart(uri, context, username)
                                AvatarImageApi.retrofitService.addImage(imagePart, username)
                                avatarImageViewModel.avatarImage =
                                    AvatarImage(uri.toString(), username)
                                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                onAddUserInfo()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Save failed", Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }
                        }

                    },
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .fillMaxWidth(0.9f),
                    colors = ButtonDefaults.buttonColors(Color(218, 128, 128))
                ) {
                    Text(text = "Save", fontFamily = rignteousFont, color = Color.Black)
                }
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FirstSetupScreenPreview() {
    FirstSetupScreen(
        context = LocalContext.current,
        username = "",
        userViewModel = UserViewModel(),
        avatarImageViewModel = AvatarImageViewModel(),
        onAddUserInfo = {}
    )
}

fun uriToMultipart(uri: Uri?, context: Context, username: String): MultipartBody.Part {
    val contentResolver: ContentResolver = context.contentResolver
    val inputStream: InputStream? = uri?.let { it ->
        contentResolver.openInputStream(it)
    }
    val byteArrayOutputStream = ByteArrayOutputStream()
    inputStream?.copyTo(byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    val imageMediaType = "image/png".toMediaType()
    val imageRequestBody = byteArray.toRequestBody(imageMediaType)
    val imagePart = MultipartBody.Part.createFormData(
        "image",
        "${username}_avatar.png",
        imageRequestBody
    )
    return imagePart
}