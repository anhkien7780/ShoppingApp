package android.kien.shoppingapp.screen

import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.AccountDrawerSheet
import android.kien.shoppingapp.library.composable.robotoMonoFont
import android.kien.shoppingapp.models.User
import android.kien.shoppingapp.navigation.Screen
import android.kien.shoppingapp.viewmodel.ProductViewModel
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.models.Product
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProductScreen(
    navController: NavController,
    user: User,
    avatarUrl: String?,
    productViewModel: ProductViewModel,
    onNavigateToCart: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToAccountSetting: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var backPressedCount by remember { mutableIntStateOf(0) }
    BackHandler {
        backPressedCount++
        if (backPressedCount == 2) {
            exitProcess(0)
        }
    }
    if (backPressedCount == 1) {
        Toast.makeText(LocalContext.current, "Press back again to exit", Toast.LENGTH_SHORT).show()
    }
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(content = {
            Spacer(modifier = Modifier.size(20.dp))
            AccountDrawerSheet(
                accountName = user.name,
                sex = user.sex,
                avatarUrl = avatarUrl,
                modifier = Modifier,
                onNavigateToAccountSetting = onNavigateToAccountSetting
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight(0.95f)
            ) {
                IconButton(
                    onClick = onLogout,
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Bottom)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Exit app",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        })
    }) {
        Scaffold(topBar = {
            var searchQuery by remember { mutableStateOf("") }
            CenterAlignedTopAppBar(navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            }, title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
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

                }
            }, actions = {
                IconButton(onClick = { onNavigateToCart() }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart, contentDescription = null
                    )
                }
            })
        }, content = { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), modifier = Modifier.padding(innerPadding)
            ) {
                items(productViewModel.productList.value!!.size) { item ->
                    ProductCard(
                        product = productViewModel.productList.value!![item]
                    ) {
                        navController.navigate(Screen.ProductDetailScreen.route + "/$item")
                    }
                }
            }
        })
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    val cardWidth = 180.dp
    val cardHeight = 330.dp
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .padding(5.dp)
            .size(width = cardWidth, height = cardHeight)
            .clickable {
                onClick()
            }

    ) {
        Column {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                AsyncImage(
                    model = product.imgSrc,
                    modifier = Modifier.size(width = cardWidth, height = 250.dp),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.FillHeight
                )
            }
            Card {
                Text(
                    text = product.productName,
                    Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = robotoMonoFont,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "$" + product.price.toString(),
                fontWeight = FontWeight.Bold,
                fontFamily = robotoMonoFont,
                fontSize = 16.sp
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        Product(
            1,
            "Product 1",
            12f,
            "https://picsum.photos/200/300",
            ""
        )
    ) {}
}