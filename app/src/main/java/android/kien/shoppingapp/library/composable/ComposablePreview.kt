package android.kien.shoppingapp.library.composable

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

// Preview
@Preview(showBackground = true)
@Composable
fun ShowLogoPreview(){
    ShoppingAppTheme {
        ShowLogo(image = R.drawable.ic_launcher_background)
    }
}
@Preview(showBackground = true)
@Composable
fun SignUpInputPreview(){
    ShoppingAppTheme {
        SignUpInput()
    }
}
@Preview(showBackground = true)
@Composable
fun RememberPasswordPreview(){
    ShoppingAppTheme{
        RememberPassword()
    }
}
@Preview(showBackground = true)
@Composable
fun SignInButtonPreview(){
    ShoppingAppTheme {
        SignInButton()
    }
}
@Preview
@Composable
fun ForgetPasswordTextButtonPreview(){
    ShoppingAppTheme {
        SignUpTextButton()
    }
}
@Preview
@Composable
fun SignUpButtonPreview(){
    ShoppingAppTheme {
        SignUpButton()
    }
}
@Preview
@Composable
fun ProductCardPreview(){
    ProductCard("Cafe meo meo meo", 54.0, R.drawable.ic_launcher_background)
}
@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    ShoppingAppTheme {
        SignInScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    ShoppingAppTheme {
        SignUpScreen()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ListProductScreen(){
    ShoppingAppTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {

                }
            }
        ) {
            Scaffold(
                topBar = {
                    MyTopBar(drawerState = drawerState, scope = scope)
                },
                content = {
                        innerPadding ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        items(productList) {
                                item ->
                            ProductCard(
                                productName = item.productName,
                                productPrice = item.productPrice,
                                productImage = item.productImage
                            )
                        }
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MyTopBarPreview(){
    ShoppingAppTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {}
            },
        ) {
            Scaffold(
                topBar = { MyTopBar(drawerState = drawerState, scope = scope) }
            ) {}

        }
    }
}
