package android.kien.shoppingapp.library.composable

import android.kien.shoppingapp.R
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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






@Composable
fun AccountDrawerSheet(
    accountName: String,
    sex: Boolean,
    avatarImage: Int,
    modifier: Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(width = 320.dp, height = 60.dp)
            .background(color = Color(214, 214, 214)),
    ) {
        Image(
            painter = painterResource(id = avatarImage),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(0.1.dp, color = Color.Transparent),
        )
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
        Column {
            Text(
                text = accountName,
                fontFamily = rignteousFont,
                fontSize = 18.sp,
            )

            Text(
                text = if (!sex) "Male" else "Female",
                fontFamily = rignteousFont,
                fontSize = 15.sp,
            )


        }
        TextButton(
            onClick = { },
            contentPadding = PaddingValues(),
            modifier = Modifier.align(Alignment.Bottom)
        ) {
            Text(
                text = "Account setting",
                fontFamily = rignteousFont,
                color = Color.Blue,
                fontSize = 15.sp
            )
        }
    }
}


// Preview


@Preview(showBackground = true)
@Composable
fun SignUpInputPreview() {
    ShoppingAppTheme {
        SignUpInput()
    }
}

@Preview(showBackground = true)
@Composable
fun RememberPasswordPreview() {
    ShoppingAppTheme {
        RememberPassword()
    }
}

@Preview
@Composable
fun SignUpTextButtonPreview() {
    ShoppingAppTheme {
        SignUpTextButton { }
    }
}



@Preview(showBackground = true)
@Composable
fun DrawerContentRowPreview() {
    ShoppingAppTheme {
        AccountDrawerSheet(
            "Flores, Juanita",
            false,
            R.drawable.avatar,
            modifier = Modifier
        )
    }
}









