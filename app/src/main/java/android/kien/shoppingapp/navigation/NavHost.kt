package android.kien.shoppingapp.navigation

import android.kien.shoppingapp.data.allUserInfo
import android.kien.shoppingapp.models.User
import android.kien.shoppingapp.screen.AddressesScreen
import android.kien.shoppingapp.screen.CartScreen
import android.kien.shoppingapp.screen.ChangeInfoScreen
import android.kien.shoppingapp.screen.ChangePasswordScreen
import android.kien.shoppingapp.screen.FirstSetupScreen
import android.kien.shoppingapp.screen.ListProductScreen
import android.kien.shoppingapp.screen.PaymentSuccessScreen
import android.kien.shoppingapp.screen.ProductDetailsScreen
import android.kien.shoppingapp.screen.SignInScreen
import android.kien.shoppingapp.screen.SignUpScreen
import android.kien.shoppingapp.screen.UserInfoSettingScreen
import android.kien.shoppingapp.viewmodel.AccountViewModel
import android.kien.shoppingapp.viewmodel.AvatarImageUiState
import android.kien.shoppingapp.viewmodel.AvatarImageViewModel
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.ProductViewModel
import android.kien.shoppingapp.viewmodel.UserUiState
import android.kien.shoppingapp.viewmodel.UserViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController(),
    cartViewModel: CartViewModel = viewModel(),
    accountViewModel: AccountViewModel = viewModel(),
    avatarImageViewModel: AvatarImageViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    productViewModel: ProductViewModel = viewModel(),
    startDestination: String = Screen.SignInScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(
                accountViewModel = accountViewModel,
                cartViewModel = cartViewModel,
                userViewModel = userViewModel,
                avatarImageViewModel = avatarImageViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) },
                onSuccessfulSignIn = { navController.navigate(Screen.ListProductsScreen.route) },
                loginUiState = accountViewModel.loginUiState
            )
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(
                context = LocalContext.current,
                accountViewModel = accountViewModel,
                cartViewModel = cartViewModel,
                registerUiState = accountViewModel.registerUiState,
                onSignUpSuccess = {
                    navController.navigate(Screen.FirstSetupScreen.route)
                }
            )
        }

        composable(route = Screen.ListProductsScreen.route) {
            if (userViewModel.userUiState == UserUiState.Loading || avatarImageViewModel.avatarImageUiState == AvatarImageUiState.Loading) {
                CircularProgressIndicator()
            } else{
                ListProductScreen(
                    navController = navController,
                    productViewModel = productViewModel,
                    avatarUrl = avatarImageViewModel.avatarImage?.url,
                    user = userViewModel.user.value!!,
                    onNavigateToCart = { navController.navigate(Screen.CartScreen.route) },
                    onLogout = {
                        accountViewModel.logout()
                        navController.navigate(route = Screen.SignInScreen.route)
                    },
                    onClick = { navController.navigate(Screen.UserInfoSettingScreen.route) }

                )
            }
        }
        composable(route = Screen.CartScreen.route) {
            CartScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                productViewModel = productViewModel,
                onBackClick = { navController.popBackStack() },
                onDeleteClick = { cartViewModel.deleteCartItem(cartViewModel.cartID, it) },
                onQuantityChange = { productID, quantity ->
                    cartViewModel.updateCartQuantity(cartViewModel.cartID, productID, quantity)
                },
                onPaymentSuccess = { navController.navigate(Screen.PaymentSuccessScreen.route) }
            )
        }

        composable(route = Screen.ProductDetailScreen.route + "/{productID}") {
            val productID = it.arguments?.getString("productID")
            if (productID != null) {
                ProductDetailsScreen(
                    context = LocalContext.current,
                    productID = productID.toInt(),
                    productViewModel = productViewModel,
                    cartViewModel = cartViewModel,
                    navController = navController,
                    onNavigateToCart = { navController.navigate(Screen.CartScreen.route) }
                )
            }
        }
        composable(route = Screen.FirstSetupScreen.route) {
            FirstSetupScreen(
                context = LocalContext.current,
                username = accountViewModel.username,
                userViewModel = userViewModel,
                avatarImageViewModel = avatarImageViewModel,
            ) {
                navController.navigate(Screen.ListProductsScreen.route)
            }
        }

        composable(route = Screen.UserInfoSettingScreen.route) {
            UserInfoSettingScreen(
                userInfo = allUserInfo[0],
                onBack = { navController.popBackStack() },
                onNavToAddresses = { navController.navigate(Screen.AddressesScreen.route) },
                onNavToChangePassword = { navController.navigate(Screen.ChangePasswordScreen.route) },
                onNavToChangeUserInfo = { navController.navigate(Screen.ChangeUserInfoScreen.route) }
            )
        }
        composable(route = Screen.AddressesScreen.route) {
            AddressesScreen(onBack = { navController.popBackStack() })
        }
        composable(route = Screen.ChangePasswordScreen.route) {
            ChangePasswordScreen(
                onBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() })
        }
        composable(route = Screen.ChangeUserInfoScreen.route) {
            ChangeInfoScreen(userInfo = User(
                "Nguyen Van Kien",
                "27/10/2002",
                27,
                true,
                "077365132",
                "anhkien7780@gmail.com"
            ),
                { navController.popBackStack() },
                onBack = { navController.popBackStack() })
        }
        composable(route = Screen.PaymentSuccessScreen.route) {
            PaymentSuccessScreen(onBackToHome = { navController.navigate(Screen.ListProductsScreen.route) })
        }

    }
}