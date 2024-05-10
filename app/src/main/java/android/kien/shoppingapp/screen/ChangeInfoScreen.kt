package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.data.UserInfo
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangeInfoScreen(userInfo: UserInfo) {
    val name by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "CHANGE INFORMATION", fontFamily = rignteousFont)
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            ChangeAvatar(avatarImage = userInfo.avatarImage)
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = name,
                label = {
                    Text(
                        text = "Change Name",
                        fontFamily = rignteousFont
                    )
                },
                placeholder = {
                    Text(
                        text = userInfo.name,
                        fontFamily = rignteousFont
                    )
                },
                onValueChange = {},
                visualTransformation = if ("".isEmpty())
                    PlaceholderTransformation(userInfo.name)
                else VisualTransformation.None,
                modifier = Modifier.fillMaxWidth(),
            )
        }

    }

}

@Composable
fun ChangeAvatar(avatarImage: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = avatarImage),
            contentDescription = "Avatar",
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.padding(30.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(PaddingValues())) {
                Icon(
                    painter = painterResource(id = R.drawable.folder_send_fill_2x),
                    contentDescription = "Folder send fill"
                )
            }
            Text(text = "Image size < 1MB", fontFamily = rignteousFont)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChangeInfoScreenPreview() {
    ShoppingAppTheme {
        ChangeInfoScreen(UserInfo())
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeAvatarPreview() {
    ChangeAvatar(avatarImage = UserInfo().avatarImage)
}

class PlaceholderTransformation(private val placeholder: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return placeholderFilter(placeholder)
    }
}

fun placeholderFilter(placeholder: String): TransformedText {

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return 0
        }

        override fun transformedToOriginal(offset: Int): Int {
            return 0
        }
    }

    return TransformedText(AnnotatedString(placeholder), numberOffsetTranslator)
}