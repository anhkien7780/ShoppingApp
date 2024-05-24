package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.data.UserInfo
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddressesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "ADDRESSES", fontFamily = rignteousFont) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

        }

    }
}

@Composable
fun AddressItem(
) {

}

@Preview(showBackground = true)
@Composable
fun AddressItemPreview() {
    ShoppingAppTheme {
        AddressItem()
    }
}

@Preview(showBackground = true)
@Composable
fun AddressesScreenPreview() {
    ShoppingAppTheme {
        AddressesScreen({})
    }
}