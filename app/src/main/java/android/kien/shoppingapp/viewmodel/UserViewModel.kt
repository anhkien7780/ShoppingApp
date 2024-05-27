package android.kien.shoppingapp.viewmodel

import android.kien.shoppingapp.models.User
import android.kien.shoppingapp.network.UserApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UserUiState {
    object Idle : UserUiState()
    object Success : UserUiState()
    object Error : UserUiState()
    object Loading : UserUiState()
}

class UserViewModel : ViewModel() {
    var userUiState: UserUiState by mutableStateOf(UserUiState.Idle)
        private set
    var user = MutableLiveData<User>()
    fun addUser(user: User) {
        viewModelScope.launch {
            try {
                userUiState = UserUiState.Loading
                UserApi.retrofitService.addNewUser(user)
                this@UserViewModel.user.value = user
                userUiState = UserUiState.Success
            } catch (e: Exception) {
                userUiState = UserUiState.Error
                println(e.message)
            }
        }
    }

    fun setUserUiStateToIdle() {
        userUiState = UserUiState.Idle
    }

    fun getUser(username: String) {
        viewModelScope.launch {
            try {
                userUiState = UserUiState.Loading
                user.value = UserApi.retrofitService.getUser(username)
                userUiState = UserUiState.Success
            } catch (e: Exception) {
                userUiState = UserUiState.Error
                println(e.message)
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            try {
                UserApi.retrofitService.updateUser(user)
                this@UserViewModel.user.value = user
                userUiState = UserUiState.Success
            } catch (e: Exception) {
                userUiState = UserUiState.Error
                println(e.message)
            }
        }
    }

}