package android.kien.shoppingapp.screen

import android.content.Context
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.viewmodel.AccountViewModel
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.RegisterUiState
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpScreen(
    context: Context,
    accountViewModel: AccountViewModel,
    cartViewModel: CartViewModel,
    registerUiState: RegisterUiState,
    onSignUpSuccess: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowLogo(logoImage = R.drawable.logo)
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email", fontFamily = rignteousFont, fontSize = 16.sp) },
                placeholder = { Text(text = "Email", fontFamily = rignteousFont, fontSize = 16.sp) },
                textStyle = TextStyle(fontFamily = rignteousFont, fontSize = 20.sp),
                singleLine = true,
                maxLines = 1
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password", fontFamily = rignteousFont, fontSize = 16.sp) },
                placeholder = { Text(text = "Password", fontFamily = rignteousFont, fontSize = 16.sp) },
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password", fontFamily = rignteousFont, fontSize = 16.sp) },
                placeholder = { Text(text = "Enter your password again", fontFamily = rignteousFont, fontSize = 16.sp) },
                textStyle = TextStyle(fontFamily = rignteousFont, fontSize = 20.sp),
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.padding(bottom = 30.dp))

            Button(colors = ButtonDefaults.buttonColors(containerColor = Color(217, 217, 217)),
                modifier = Modifier.size(width = 150.dp, height = 50.dp),
                onClick = {
                    accountViewModel.register(email, password)
                }) {

                when (registerUiState) {
                    is RegisterUiState.Idle -> {
                        Text(
                            text = "Sign up",
                            fontFamily = rignteousFont,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }
                    is RegisterUiState.Loading -> {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()
                        }
                    }

                    is RegisterUiState.Success -> {
                        Toast.makeText(context, "Resister Success", Toast.LENGTH_SHORT).show()
                        cartViewModel.addNewCart(email)
                        onSignUpSuccess()
                        accountViewModel.registerUiState = RegisterUiState.Idle
                    }

                    is RegisterUiState.Error -> {
                        Toast.makeText(context, "Resister Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}


@Composable
fun ShowLogo(
    logoImage: Int, modifier: Modifier = Modifier, logoSize: Dp = 200.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = logoImage),
            contentDescription = "Logo",
            modifier = modifier.size(logoSize)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "Shopping App",
            fontSize = 30.sp,
            fontFamily = rignteousFont,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        context = LocalContext.current,
        accountViewModel = viewModel(modelClass = AccountViewModel::class.java),
        cartViewModel = viewModel(modelClass = CartViewModel::class.java),
        registerUiState = RegisterUiState.Idle,
        onSignUpSuccess = {}
    )

}


