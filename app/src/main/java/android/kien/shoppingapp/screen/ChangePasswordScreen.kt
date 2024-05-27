package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.content.Context
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.models.Account
import android.kien.shoppingapp.network.AccountApi
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(onBack: () -> Unit, username: String) {
    val context: Context = LocalContext.current
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "CHANGE PASSWORD",
                fontFamily = rignteousFont,
            )
        }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        })
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = oldPassword,
                onValueChange = { oldPassword = it },
                label = { Text(text = "Old Password", fontFamily = rignteousFont) },
                placeholder = { Text(text = "Enter password", fontFamily = rignteousFont) },
                textStyle = TextStyle(fontFamily = rignteousFont),
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text(text = "New Password", fontFamily = rignteousFont, fontSize = 16.sp) },
                placeholder = { Text(text = "Enter password", fontFamily = rignteousFont, fontSize = 16.sp) },
                textStyle = TextStyle(fontFamily = rignteousFont, fontSize = 20.sp),
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password", fontFamily = rignteousFont, fontSize = 16.sp) },
                placeholder = { Text(text = "Enter password", fontFamily = rignteousFont, fontSize = 16.sp) },
                textStyle = TextStyle(fontFamily = rignteousFont, fontSize = 20.sp),
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                        if (newPassword == confirmPassword) {
                            scope.launch {
                                try {
                                    AccountApi.retrofitService.changePassword(
                                        account = Account(username, newPassword),
                                        oldPassword = oldPassword
                                    )
                                    Toast.makeText(context, "Password changed", Toast.LENGTH_SHORT)
                                        .show()
                                } catch (e: Exception) {
                                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    colors = ButtonDefaults.buttonColors(Color(218, 128, 128))
                ) {
                    Text(text = "Save", fontFamily = rignteousFont, color = Color.Black)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    ShoppingAppTheme {
        ChangePasswordScreen({}, "")
    }
}