package android.kien.shoppingapp.library.composable

import android.kien.shoppingapp.R
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation


val rignteousFont = FontFamily(
    Font(R.font.righteous_regular, FontWeight.Normal)
)

val robotoMonoFont = FontFamily(
    Font(R.font.roboto_mono_italic_variable_font_weight, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_mono_variable_font_weight, FontWeight.Normal)
)


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    singleLine: Boolean = false,
    description: String = "",
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label
            )
        },
        placeholder = {
            Text(
                text = placeholder
            )
        },
        singleLine = singleLine,
        visualTransformation = (if (isPassword) PasswordVisualTransformation() else null)!!
    )
}

// Text input
@Composable
fun EmailInput() {
    var textValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = textValue,
        label = {
            Text(
                text = "Email"
            )
        },
        placeholder = {
            Text(
                text = "Enter your email"
            )
        },
        onValueChange = { textValue = it },
        singleLine = true,
    )
}

@Composable
fun PasswordInput() {
    var textValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = "",
        label = {
            Text(
                text = "Password"
            )
        },
        placeholder = {
            Text(
                text = "Enter your password"
            )
        },
        onValueChange = { textValue = it },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ConfirmPasswordInput() {
    OutlinedTextField(
        value = "",
        label = {
            Text(
                text = "Confirm Password"
            )
        },
        placeholder = {
            Text(
                text = "Enter your password again"
            )
        },
        onValueChange = {},
        singleLine = true,
    )
}

@Composable
fun SignUpInput() {
    Column {
        EmailInput()
        PasswordInput()
    }
}


// Check box
@Composable
fun RememberPassword() {

}


@Composable
fun SignUpTextButton(onNavigateToSignUp: () -> Unit) {
    TextButton(
        onClick = onNavigateToSignUp
    ) {
        Text(
            text = "I don't have account yet!",
            color = Color.Black,
            fontFamily = robotoMonoFont,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
        )
    }
}














