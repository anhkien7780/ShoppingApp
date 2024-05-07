package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.library.composable.robotoMonoFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen() {
    var searchQuery by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            title = {
                ProvideTextStyle(
                    value = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = robotoMonoFont,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onSearch = {},
                        active = false,
                        onActiveChange = {},
                        placeholder = { Text(text = "Search something!") },
                        trailingIcon = {
                            IconButton(
                                onClick = {},
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                )
                            }
                        },
                    ) {

                    }
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart"
                    )

                }
            }
        )
    }) {

    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsScreenPreview() {
    ShoppingAppTheme {
        ProductDetailsScreen()
    }
}

