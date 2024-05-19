 package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.data.allAccounts
import android.kien.shoppingapp.library.composable.SignUpTextButton
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.library.composable.robotoMonoFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

 @SuppressLint("UnrememberedMutableState")
@Composable
fun SignInScreen(
     onNavigateToSignUp: () -> Unit,
     onSuccessfulSignIn: () -> Unit,
    ){
    var email by remember{mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var rememberState by remember { mutableStateOf(false) }
    var signUpSuccessful by remember { mutableStateOf(true) }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Column(horizontalAlignment = Alignment.Start){
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                ShowLogo(logoImage = R.drawable.logo)
                Spacer(modifier = Modifier.height(50.dp))
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
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = rememberState,
                    onCheckedChange = {rememberState = it},
                )
                Text(
                    text = "Remember me"
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Button(
            onClick = {
                for(account in allAccounts)
                    signUpSuccessful = (account.email == email) && (account.password == password)
                if(signUpSuccessful) {
                        onSuccessfulSignIn()
                } else{
                    signUpSuccessful = false
                }
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
            text =
            if(signUpSuccessful)
                ""
             else
                "Sorry, your password is incorrect or your account isn’t exits",
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontFamily = robotoMonoFont,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    ShoppingAppTheme {
        SignInScreen({},{})
    }
}