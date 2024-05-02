package android.kien.shoppingapp.navigation

sealed class Screen (val route: String){
    data object SignInScreen: Screen("sign_in_screen")
    data object SignUpScreen: Screen("sign_up_screen")
}