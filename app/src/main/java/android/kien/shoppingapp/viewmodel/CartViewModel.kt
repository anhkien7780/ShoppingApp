package android.kien.shoppingapp.viewmodel

import android.kien.shoppingapp.models.CartItem
import android.kien.shoppingapp.network.CartApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class CartUiState {
    object Idle : CartUiState()
    object Loading : CartUiState()
    object Success : CartUiState()
    object Error : CartUiState()
}

class CartViewModel : ViewModel() {
    var cartUiState: CartUiState by mutableStateOf(CartUiState.Idle)
    var cartID by mutableIntStateOf(-1)
        private set
    var listCartItems = MutableLiveData<List<CartItem>>()
        private set
    private fun _getCart(username: String) {
        viewModelScope.launch {
            try {
                val cart = CartApi.retrofitService.getCart(username)
                cartID = cart.cartID
                listCartItems.value = cart.listCartItem
            } catch (e: Exception) {
                e.printStackTrace()
                println("Get cart failed")
            }
        }
    }

    fun getCart(username: String) {
        _getCart(username)
    }

    fun addNewCart(username: String) {
        viewModelScope.launch {
            try {
                val cart = CartApi.retrofitService.addNewCart(username)
                cartID = cart.cartID
                listCartItems.value = emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                println("Add new cart failed")
            }
        }
    }

    fun addToCart(cartID: Int, productID: Int) {
        viewModelScope.launch {
            try {
                var foundCartItem = false
                val currentList = listCartItems.value ?: emptyList()
                cartUiState = CartUiState.Loading
                CartApi.retrofitService.addToCart(cartID, productID)
                val newList = currentList.map { cartItem ->
                    if (cartItem.productID == productID) {
                        foundCartItem = true
                        cartItem.copy(quantity = cartItem.quantity + 1)
                    } else {
                        cartItem
                    }
                }
                if (!foundCartItem) {
                    listCartItems.value = newList + CartItem(productID, 1)
                } else {
                    listCartItems.value = newList
                }
                cartUiState = CartUiState.Success
            } catch (e: Exception) {
                cartUiState = CartUiState.Error
                e.printStackTrace()
                println("Add to cart failed")
            } finally {
                cartUiState = CartUiState.Idle
            }
        }
    }

    fun updateCartQuantity(cartID: Int, productID: Int, quantity: Int) {
        viewModelScope.launch {
            try {
                CartApi.retrofitService.updateCartItemQuantity(cartID, productID, quantity)
            } catch (e: Exception) {
                e.printStackTrace()
                println("Update cart failed")
            }
        }
    }

    fun deleteCartItem(cartID: Int, productID: Int) {
        viewModelScope.launch {
            try {
                CartApi.retrofitService.deleteCartItem(cartID, productID)
            } catch (e: Exception) {
                e.printStackTrace()
                println("Delete cart failed")
            }
        }
    }
}