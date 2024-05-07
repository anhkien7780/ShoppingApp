package android.kien.shoppingapp.screen

import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.AccountDrawerSheet
import android.kien.shoppingapp.library.composable.ProductCard
import android.kien.shoppingapp.library.composable.productList
import android.kien.shoppingapp.library.composable.robotoMonoFont
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProductScreen(onLogout: () -> Unit) {
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
                "Flores, Juanita", false, R.drawable.avatar, modifier = Modifier
            )
            Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight(0.95f)) {

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
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart, contentDescription = null
                    )
                }
            })
        }, content = { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), modifier = Modifier.padding(innerPadding)
            ) {
                items(productList) { item ->
                    ProductCard(
                        productName = item.productName,
                        productPrice = item.productPrice,
                        productImage = item.productImage
                    )
                }
            }
        })
    }
}


@Preview(showBackground = true)
@Composable
fun ListProductScreenPreview() {
    ShoppingAppTheme {
        ListProductScreen {}
    }
}
