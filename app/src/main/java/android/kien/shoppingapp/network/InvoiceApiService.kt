package android.kien.shoppingapp.network

import android.kien.shoppingapp.models.Invoice
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InvoiceApiService{
    @GET("/invoices/{username}")
    suspend fun getInvoices(@Path("username") username: String): List<Invoice>
    @POST("/invoices/add")
    suspend fun addInvoice(@Body invoice: Invoice)
}

object InvoiceApi{
    val retrofitService: InvoiceApiService by lazy {
        retrofit.create(InvoiceApiService::class.java)
    }
}