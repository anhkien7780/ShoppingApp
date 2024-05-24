package android.kien.shoppingapp.viewmodel

import android.kien.shoppingapp.network.ProductApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.models.Product
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var productList = MutableLiveData<List<Product>>()
        private set
    private fun _getProducts() {
        viewModelScope.launch {
            try {
                productList.value = ProductApi.retrofitService.getProducts()
            } catch (e: Exception){
                productList.value = emptyList()
            }
        }
    }
    init {
        _getProducts()
    }
}