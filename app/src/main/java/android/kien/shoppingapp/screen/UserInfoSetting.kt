package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.data.UserInfo
import android.kien.shoppingapp.library.composable.rignteousFont
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserSettingScreen(
    userInfo: UserInfo
){
    Scaffold (
       topBar = {
           CenterAlignedTopAppBar(
               title = { Text(text = "ACCOUNT SETTING", fontFamily = rignteousFont) },
               navigationIcon = {
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back button")
                   }
               }
           )
       }
    ) {innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            UserInfoComponent(userInfo = userInfo)
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.padding(10.dp))
            MyButton("Change Information")
            MyButton("Change Password")
            MyButton("Addresses")
        }

    }
}

@Composable
fun UserInfoComponent(userInfo: UserInfo){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Image(
            painter = painterResource(id = userInfo.avatarImage),
            contentDescription = "Avatar",
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column {
            Text(text = userInfo.name, fontFamily = rignteousFont, fontSize = 20.sp)
            Text(text = userInfo.gender, fontFamily = rignteousFont)
            Text(text = userInfo.phoneNumber, fontFamily = rignteousFont)
        }
    }
}

@Composable
fun MyButton(buttonText: String){
    TextButton(onClick = { /*TODO*/ }, modifier = Modifier
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
fun AccountSettingScreenPreView(){
    ShoppingAppTheme {
        UserSettingScreen(UserInfo())
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoPreview(){
    UserInfoComponent(userInfo = UserInfo())
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview(){
    ShoppingAppTheme {
        MyButton("My Button")
    }
}