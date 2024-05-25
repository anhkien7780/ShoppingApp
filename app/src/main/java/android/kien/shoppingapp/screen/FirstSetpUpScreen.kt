package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.content.Context
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.models.Date
import android.kien.shoppingapp.models.User
import android.kien.shoppingapp.viewmodel.AvatarImageUiState
import android.kien.shoppingapp.viewmodel.AvatarImageViewModel
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.UserUiState
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            ChangeAvatar(placeholder = R.drawable.default_logo, onUriChange = {uri = it})
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
            when(userViewModel.userUiState){
                is UserUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is UserUiState.Success -> {
                    avatarImageViewModel.uploadAvatarImage(uri, context, username)
                    userViewModel.getUser(username)
                    userViewModel.setUserUiStateToIdle()
                    onAddUserInfo()
                }
                is UserUiState.Error -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                is UserUiState.Idle -> {}
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        userViewModel.addUser(
                            User(
                                name,
                                birthDay.format(DateTimeFormatter.ofPattern("d/MM/yyyy")),
                                LocalDate.now().year - birthDay.year,
                                sex,
                                phoneNumber,
                                username
                            )
                        )
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
