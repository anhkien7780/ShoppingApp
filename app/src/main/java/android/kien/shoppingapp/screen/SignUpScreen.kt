package android.kien.shoppingapp.screen

import android.content.Context
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.ShowLogo
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(context: Context, onSignUpSuccess: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        var username by remember{ mutableStateOf("") }
        var email by remember{ mutableStateOf("") }
        var password by remember {mutableStateOf("")}
        var confirmPassword by remember {mutableStateOf("")}
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowLogo(logoImage = R.drawable.ic_launcher_background)
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
            OutlinedTextField(
                value = username,
                onValueChange = {username = it},
                label = { Text(text = "Full name") },
                placeholder = { Text(text = "Enter your full name") },
                singleLine = true,
                maxLines = 1
            )
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Email") },
                singleLine = true,
                maxLines = 1
            )
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") },
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {confirmPassword = it},
                label = { Text(text = "Confirm Password") },
                placeholder = { Text(text = "Enter your password again") },
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(217,217,217)),
                modifier = Modifier.size(width = 150.dp, height = 50.dp),
                onClick = {
                    if(password == confirmPassword){
                        onSignUpSuccess()
                    } else{
                        Toast.makeText(context, "Password does not match", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(
                    text = "Sign up",
                    fontFamily = rignteousFont,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    ShoppingAppTheme {
        SignUpScreen(context = LocalContext.current) {}
    }
}