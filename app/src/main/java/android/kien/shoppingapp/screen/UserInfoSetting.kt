package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.models.User
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserInfoSettingScreen(
    user: User,
    avatarImageUrl: String,
    onBack: () -> Unit = {},
    onNavToChangeUserInfo: () -> Unit = {},
    onNavToChangePassword: () -> Unit = {},
    onNavToAddresses: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "ACCOUNT SETTING", fontFamily = rignteousFont) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            UserInfoComponent(user = user, avatarImageUrl = avatarImageUrl)
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.padding(10.dp))
            MyButton("Change Information", onNavToChangeUserInfo)
            MyButton("Change Password", onNavToChangePassword)
            MyButton("Addresses", onNavToAddresses)
        }

    }
}

@Composable
fun UserInfoComponent(user: User, avatarImageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = avatarImageUrl,
            contentDescription = "Avatar",
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column {
            Text(text = user.name, fontFamily = rignteousFont, fontSize = 20.sp)
            Text(text = if (user.sex) "Male" else "Female", fontFamily = rignteousFont)
            Text(text = user.phoneNumber, fontFamily = rignteousFont)
        }
    }
}

@Composable
fun MyButton(buttonText: String, onNavToFunction: () -> Unit = {}) {
    TextButton(
        onClick = { onNavToFunction() }, modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(width = 1.dp, color = Color.Black)
    ) {
        Text(
            text = buttonText,
            fontFamily = rignteousFont,
            fontSize = 20.sp,
            color = Color.Black
        )

    }
}


@Preview(showBackground = true)
@Composable
fun AccountSettingScreenPreView() {
    ShoppingAppTheme {
        UserInfoSettingScreen(
            User(
                name = "Flores",
                birthDay = "27/10/2002",
                age = 22,
                sex = true,
                phoneNumber = "0987654321",
                username = ""
            ),
            avatarImageUrl = null.toString()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoPreview() {
    UserInfoComponent(
        User(
            name = "Flores",
            birthDay = "27/10/2002",
            age = 22,
            sex = true,
            phoneNumber = "0987654321",
            username = ""
        ), avatarImageUrl = null.toString()
    )
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview() {
    ShoppingAppTheme {
        MyButton("My Button")
    }
}