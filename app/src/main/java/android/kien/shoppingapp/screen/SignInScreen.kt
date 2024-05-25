package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.SignUpTextButton
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.library.composable.robotoMonoFont
import android.kien.shoppingapp.viewmodel.AccountViewModel
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.LoginUiState
import android.kien.shoppingapp.viewmodel.UserViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@Composable
fun SignInScreen(
    cartViewModel: CartViewModel,
    accountViewModel: AccountViewModel,
    userViewModel: UserViewModel,
    onNavigateToSignUp: () -> Unit,
    onSuccessfulSignIn: () -> Unit,
    loginUiState: LoginUiState
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberState by remember { mutableStateOf(false) }
    var wrongPassword by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ShowLogo(logoImage = R.drawable.logo)
                Spacer(modifier = Modifier.height(50.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    placeholder = { Text(text = "Email") },
                    singleLine = true,
                    maxLines = 1
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberState,
                    onCheckedChange = { rememberState = it },
                )
                Text(
                    text = "Remember me"
                )
            }
        }
        when (loginUiState) {
            is LoginUiState.Idle -> {}
            is LoginUiState.Loading -> {CircularProgressIndicator()}
            is LoginUiState.Success -> {
                onSuccessfulSignIn()
                userViewModel.getUser(accountViewModel.username)
                cartViewModel.getCart(accountViewModel.username)
            }
            is LoginUiState.Error -> {wrongPassword = true}
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Button(
            onClick = {
                accountViewModel.login(email, password)
                wrongPassword = false
            },
            modifier = Modifier.size(width = 150.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(217, 217, 217))
        ) {
            Text(
                text = "Sign In",
                fontFamily = rignteousFont,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }

        SignUpTextButton(onNavigateToSignUp)
        Text(
            text = if (wrongPassword) "Sorry, your password is incorrect or your account isn’t exits"
            else "",
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontFamily = robotoMonoFont,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
    }
}



