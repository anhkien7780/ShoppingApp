package android.kien.shoppingapp.navigation

import android.kien.shoppingapp.screen.ListProductScreen
import android.kien.shoppingapp.screen.SignInScreen
import android.kien.shoppingapp.screen.SignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
            SignInScreen(
                onNavigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) },
                onSuccessfulSignIn = { navController.navigate(Screen.ListProductsScreen.route)}
            )
        }
        composable(route = Screen.SignUpScreen.route){
            SignUpScreen(
                context = LocalContext.current,
                onSignUpSuccess = { navController.navigate(Screen.ListProductsScreen.route) }
            )
        }

        composable(route = Screen.ListProductsScreen.route){
            ListProductScreen(
                onLogout = { navController.navigate(Screen.SignInScreen.route) }
            )
        }
    }
}