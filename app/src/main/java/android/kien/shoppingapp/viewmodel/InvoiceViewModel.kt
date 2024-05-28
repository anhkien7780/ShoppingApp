package android.kien.shoppingapp.viewmodel

import android.kien.shoppingapp.models.Invoice
import android.kien.shoppingapp.network.InvoiceApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class InvoiceUiState {
    object Success : InvoiceUiState()
    object Error : InvoiceUiState()
    object Loading : InvoiceUiState()
    object Idle : InvoiceUiState()
}
class InvoiceViewModel : ViewModel() {
    val allInvoices = MutableLiveData<List<Invoice>>()
    var invoiceUiState by mutableStateOf<InvoiceUiState>(InvoiceUiState.Idle)
        private set
    fun getInvoices(username: String) {
        viewModelScope.launch {
            try {
                invoiceUiState = InvoiceUiState.Loading
                allInvoices.value = InvoiceApi.retrofitService.getInvoices(username)
                invoiceUiState = InvoiceUiState.Success
            } catch (e: Exception) {
                invoiceUiState = InvoiceUiState.Error
                e.printStackTrace()
            }
        }
    }
    fun addInvoice(invoice: Invoice){
        viewModelScope.launch {
            try {
                invoiceUiState = InvoiceUiState.Loading
                InvoiceApi.retrofitService.addInvoice(invoice)
                allInvoices.value = listOf(invoice) + allInvoices.value.orEmpty()
                invoiceUiState = InvoiceUiState.Success
            } catch (e: Exception) {
                invoiceUiState = InvoiceUiState.Error
                e.printStackTrace()
            }
        }
    }
    fun setInvoiceUiStateToIdle() {
        invoiceUiState = InvoiceUiState.Idle
    }
}