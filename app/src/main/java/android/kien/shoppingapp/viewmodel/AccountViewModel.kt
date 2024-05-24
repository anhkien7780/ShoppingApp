package android.kien.shoppingapp.viewmodel

import android.kien.shoppingapp.models.Account
import android.kien.shoppingapp.network.AccountApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle: LoginUiState()
    object Loading : LoginUiState()
    data class Success(val username: String) : LoginUiState()
    data class Error(val exception: Exception) : LoginUiState()
}
sealed class LogoutUiState {
    object Idle: LogoutUiState()
    object Loading : LogoutUiState()
    data class Success(val username: String) : LogoutUiState()
    data class Error(val exception: Exception) : LogoutUiState()
}

sealed class RegisterUiState {
    object Idle: RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val username: String) : RegisterUiState()
    data class Error(val exception: Exception) : RegisterUiState()
}

class AccountViewModel : ViewModel() {
    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Idle)
        private set
    var registerUiState: RegisterUiState by mutableStateOf(RegisterUiState.Idle)
    var username: String by mutableStateOf("")
        private set
    fun logout(){
        username = ""
        loginUiState = LoginUiState.Idle
        registerUiState = RegisterUiState.Idle
    }
    private fun _login(username: String, password: String) {
        viewModelScope.launch {
            try {
                AccountApi.retrofitService.login(Account(username, password))
                this@AccountViewModel.username = username
                loginUiState = LoginUiState.Success(username)
            } catch (e: Exception) {
                loginUiState = LoginUiState.Error(e)
            } finally {
                LoginUiState.Idle
            }
        }
    }

    private fun _register(username: String, password: String){
        viewModelScope.launch {
            try {
                registerUiState = RegisterUiState.Loading
                AccountApi.retrofitService.register(Account(username, password))
                this@AccountViewModel.username = username
                registerUiState = RegisterUiState.Success(username)
            } catch (e: Exception) {
                e.printStackTrace()
                registerUiState = RegisterUiState.Error(e)
            }
        }
    }
    fun register(username: String, password: String) {
        _register(username, password)
    }

    fun login(username: String, password: String) {
        _login(username, password)
    }
}

