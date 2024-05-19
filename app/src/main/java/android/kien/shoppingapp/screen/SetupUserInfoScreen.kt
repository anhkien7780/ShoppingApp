package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.data.Date
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import android.os.Build
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetupScreen() {
    var name by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    var birthday by remember {
        mutableStateOf(Date(1, 1, 1900))
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "SETUP STEP",
                fontFamily = rignteousFont
            )
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            ChangeAvatar(avatarImage = R.drawable.default_logo)
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.padding(10.dp))
            MyOutlinedTextFiled(
                value = name,
                onValuedChange = { name = it },
                label = "Your Name",
                placeholder = "Enter your name"
            )

            Spacer(modifier = Modifier.padding(10.dp))
            GenderSelection(gender = gender) { gender = it }
            Spacer(modifier = Modifier.padding(10.dp))
            BirthdaySelection(birthday = birthday) {
                birthday = it
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .fillMaxWidth(0.9f),
                    colors = ButtonDefaults.buttonColors(Color(218, 128, 128))
                ) {
                    Text(text = "Continue", fontFamily = rignteousFont, color = Color.Black)
                }
            }
        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SetupScreenPreview(){
    ShoppingAppTheme {
        SetupScreen()
    }
}