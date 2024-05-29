package android.kien.shoppingapp.navigation

import android.kien.shoppingapp.screen.AddressesScreen
import android.kien.shoppingapp.screen.CartScreen
import android.kien.shoppingapp.screen.ChangeInfoScreen
import android.kien.shoppingapp.screen.ChangePasswordScreen
import android.kien.shoppingapp.screen.FirstSetupScreen
import android.kien.shoppingapp.screen.ListProductScreen
import android.kien.shoppingapp.screen.PaymentScreen
import android.kien.shoppingapp.screen.PaymentSuccessScreen
import android.kien.shoppingapp.screen.ProductDetailsScreen
import android.kien.shoppingapp.screen.SignInScreen
import android.kien.shoppingapp.screen.SignUpScreen
import android.kien.shoppingapp.screen.UserInfoSettingScreen
import android.kien.shoppingapp.viewmodel.AccountViewModel
import android.kien.shoppingapp.viewmodel.AvatarImageViewModel
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.InvoiceViewModel
import android.kien.shoppingapp.viewmodel.ProductViewModel
import android.kien.shoppingapp.viewmodel.UserUiState
import android.kien.shoppingapp.viewmodel.UserViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    invoiceViewModel: InvoiceViewModel = viewModel(),
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
            if (userViewModel.userUiState == UserUiState.Loading) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            } else {
                ListProductScreen(
                    navController = navController,
                    productViewModel = productViewModel,
                    avatarImageViewModel = avatarImageViewModel,
                    user = userViewModel.user.value!!,
                    onNavigateToCart = { navController.navigate(Screen.CartScreen.route) },
                    onLogout = {
                        accountViewModel.logout()
                        navController.navigate(route = Screen.SignInScreen.route)
                    },
                    onNavigateToAccountSetting = { navController.navigate(Screen.UserInfoSettingScreen.route) }

                )
            }
        }
        composable(route = Screen.CartScreen.route) {
            CartScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                productViewModel = productViewModel,
                onBackClick = { navController.popBackStack() },
                onPayment = { navController.navigate(Screen.PaymentScreen.route) }
            )
        }

        composable(route = Screen.ProductDetailScreen.route + "/{item}") {
            val item = it.arguments?.getString("item")
            if (item != null) {
                ProductDetailsScreen(
                    context = LocalContext.current,
                    item = item.toInt(),
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
                user = userViewModel.user.value!!,
                avatarImageUrl = avatarImageViewModel.avatarImage!!.url,
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
                username = accountViewModel.username
            )
        }
        composable(route = Screen.ChangeUserInfoScreen.route) {
            ChangeInfoScreen(
                userInfo = userViewModel.user.value!!,
                username = accountViewModel.username,
                context = LocalContext.current,
                userViewModel = userViewModel,
                avatarImageViewModel = avatarImageViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(route = Screen.PaymentSuccessScreen.route) {
            PaymentSuccessScreen(onBackToHome = { navController.navigate(Screen.ListProductsScreen.route) })
        }

        composable(route = Screen.PaymentScreen.route) {
            PaymentScreen(
                username = accountViewModel.username,
                invoiceViewModel = invoiceViewModel,
                cartViewModel = cartViewModel,
                userViewModel = userViewModel,
                productViewModel = productViewModel,
                onBack = { navController.popBackStack() },
                onNavToPaymentSuccessScreen = { navController.navigate(Screen.PaymentSuccessScreen.route) }
            )
        }
    }
}