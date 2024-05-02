package android.kien.shoppingapp.library.composable

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
fun SignUpTextButtonPreview(){
    ShoppingAppTheme {
        SignUpTextButton { }
    }
}
@Preview(showBackground = true)
@Composable
fun SignUpButtonPreview(){
    ShoppingAppTheme {
        SignUpButton()
    }
}
@Preview(showBackground = true)
@Composable
fun ProductCardPreview(){
    ProductCard("Cafe meo meo meo", 54.0, R.drawable.ic_launcher_background)
}
@Preview(showBackground = true)
@Composable
fun DrawerContentRowPreview(){
    ShoppingAppTheme {
        AccountDrawerSheet("Flores, Juanita", false, R.drawable.avatar)
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
@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    ShoppingAppTheme {
        SignInScreen{  }
    }
}
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    ShoppingAppTheme {
        SignUpScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun ListProductScreenPreview(){
    ShoppingAppTheme {
        ListProductScreen()
    }
}


