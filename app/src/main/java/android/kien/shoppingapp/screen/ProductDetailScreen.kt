package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.data.Product
import android.kien.shoppingapp.data.allProducts
import android.kien.shoppingapp.library.composable.robotoMonoFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen(product: Product) {
    var searchQuery by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
                )
            }
        }, title = {
            ProvideTextStyle(
                value = TextStyle(
                    fontSize = 20.sp, fontFamily = robotoMonoFont, fontWeight = FontWeight.Normal
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
        }, actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart"
                )

            }
        })
    }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(vertical = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = product.images[0]),
                    contentDescription = "Product Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillHeight
                )
            }
            Text(
                text = product.name,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 20.dp),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold, fontFamily = robotoMonoFont, fontSize = 20.sp
                )
            )
            Text(
                text = "\$" + product.price.toString(),
                modifier = Modifier.align(Alignment.Start),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold, fontFamily = robotoMonoFont, fontSize = 20.sp
                )
            )
            HorizontalDivider(
                thickness = 2.dp
            )
            Text(
                text = "Product description: ",
                modifier = Modifier.align(Alignment.Start),
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontFamily = robotoMonoFont, fontSize = 20.sp
                )
            )
            Text(
                text = product.description,
                modifier = Modifier.align(Alignment.Start),
                style = TextStyle(
                    fontWeight = FontWeight.Normal, fontFamily = robotoMonoFont, fontSize = 18.sp
                )
            )
            Row(modifier = Modifier.fillMaxHeight()) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Bottom)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(Color(220, 170, 170))
                ) {
                    Text(
                        text = "ADD TO CART",
                        color = Color.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold, fontFamily = robotoMonoFont, fontSize = 20.sp
                        )
                    )
                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun ProductDetailsScreenPreview() {
    ShoppingAppTheme {
        ProductDetailsScreen(allProducts[0])
    }
}

