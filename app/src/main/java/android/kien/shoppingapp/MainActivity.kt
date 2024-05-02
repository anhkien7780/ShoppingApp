package android.kien.shoppingapp

import android.kien.shoppingapp.navigation.MyAppNavHost
import android.kien.shoppingapp.navigation.Screen
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MyAppNavHost(navController = navController, startDestination = Screen.SignInScreen.route)
                }
            }
        }
    }
}


