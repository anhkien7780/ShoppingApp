package android.kien.shoppingapp.navigation

sealed class Screen (val route: String){
    data object SignInScreen: Screen("sign_in_screen")
    data object SignUpScreen: Screen("sign_up_screen")
    data object ListProductsScreen: Screen("list_products_screen")
    data object ProductDetailScreen: Screen("product_detail_screen")
    data object CartScreen: Screen("cart_screen")
    data object FirstSetupScreen: Screen("first_setup_screen")

    data object UserInfoSettingScreen: Screen("user_info_setting_screen")
    data object ChangePasswordScreen: Screen("change_password_screen")
    data object ChangeUserInfoScreen: Screen("change_user_info_screen")
    data object AddressesScreen: Screen("addresses_screen")
    data object PaymentSuccessScreen: Screen("payment_success_screen")
    data object PaymentScreen: Screen("payment_screen")
}