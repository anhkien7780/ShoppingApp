package android.kien.shoppingapp.navigation

import android.kien.shoppingapp.library.composable.SignInScreen
import android.kien.shoppingapp.library.composable.SignUpScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.SignInScreen.route
){
    NavHost(navController = navController, startDestination = startDestination){
        composable(route = Screen.SignInScreen.route){
            SignInScreen(onNavigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) })
        }
        composable(route = Screen.SignUpScreen.route){
            SignUpScreen()
        }
    }
}