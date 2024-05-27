package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.library.composable.robotoMonoFont
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.ProductViewModel
import android.kien.shoppingapp.viewmodel.UserViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    cartViewModel: CartViewModel,
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel,
    onBack: () -> Unit
) {
    val listCartItems = cartViewModel.listCartItems.collectAsState()
    var address by remember { mutableStateOf("") }
    val subTotal = derivedStateOf {
        var total = 0.0f
        listCartItems.value.forEach { cartItem ->
            val product = productViewModel.productList.value!![cartItem.productID - 1]
            total += product.price * cartItem.quantity
        }
        total
    }
    val shippingFee = 0.8f
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "PAYMENT", fontFamily = rignteousFont) },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.height(15.dp))
            Box (Modifier.weight(1f)){
                LazyColumn {
                    items(listCartItems.value.size) { item ->
                        val productID = listCartItems.value[item].productID
                        //Because productID is stored in sql start with 1, but in compose it starts with 0
                        //in the list so, we need minus 1 to get the correct index
                        val product = productViewModel.productList.value!![productID - 1]
                        CartItemPaymentRow(
                            productImageUrl = product.imgSrc,
                            productName = product.productName,
                            productPrice = product.price,
                            quantity = listCartItems.value[item].quantity
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                        DeliveryAddressRow(
                            userViewModel.user.value!!.name,
                            userViewModel.user.value!!.phoneNumber,
                            onAddressChange = { address = it }
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DeliveryDateRow()
                        Spacer(modifier = Modifier.height(15.dp))
                        PaymentDetailRow(subTotal.value, shippingFee)
                    }
                }
            }
            Row (Modifier.fillMaxWidth()){
                Box(modifier = Modifier.align(Alignment.Bottom)) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(220, 170, 170)
                        )
                    ) {
                        val subTotalBigNum = BigDecimal(subTotal.value.toString())
                        val shippingFeeBigNum = BigDecimal(shippingFee.toString())
                        val totalBigNum = subTotalBigNum.add(shippingFeeBigNum)
                        Text(
                            text = "$$totalBigNum",
                            fontSize = 20.sp,
                            fontFamily = robotoMonoFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemPaymentRow(
    productImageUrl: String = "",
    productName: String = "Product 1",
    productPrice: Float = 10.0f,
    quantity: Int = 5,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(1.dp, color = Color.Black)
    ) {
        Box(Modifier.size(70.dp)) {
            AsyncImage(
                model = productImageUrl,
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = "Item Image",
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.padding(end = 5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = productName,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("$$productPrice", fontWeight = FontWeight.Bold)
                Text(
                    "X$quantity",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
    }
}

@Composable
fun DeliveryAddressRow(
    name: String = "Name",
    phoneNumber: String = "0123456789",
    onAddressChange: (String) -> Unit = {}
) {
    var address by remember { mutableStateOf("") }
    HorizontalDivider(thickness = 2.dp, color = Color.Black)
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.address_icon),
                contentDescription = "delivery address icon",
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .size(20.dp)
            )
            Text(text = "Delivery address:", fontFamily = rignteousFont, fontSize = 15.sp)
        }
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 5.dp)) {
            Text(text = name, fontFamily = rignteousFont, fontSize = 15.sp)
            Text(text = phoneNumber, fontFamily = rignteousFont, fontSize = 15.sp)
            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    onAddressChange(it)
                },
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                textStyle = TextStyle(fontSize = 15.sp, fontFamily = rignteousFont),
                placeholder = {
                    Text(
                        text = "Enter your delivery address",
                        style = TextStyle(fontSize = 15.sp, fontFamily = rignteousFont)
                    )
                },
                singleLine = true
            )
        }
    }
    HorizontalDivider(thickness = 2.dp, color = Color.Black)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DeliveryDateRow() {
    HorizontalDivider(thickness = 2.dp, color = Color.Black)
    Row(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp)) {
        val localDate = LocalDate.now()
        val startDate = localDate.plusDays(5)
        val endDate = localDate.plusDays(10)
        val start = startDate.dayOfMonth.toString() + "/" + startDate.monthValue.toString()
        val end = endDate.dayOfMonth.toString() + "/" + endDate.monthValue.toString()
        Icon(
            painter = painterResource(id = R.drawable.car_icon),
            contentDescription = "Car icon",
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = "Guaranteed to get by $start to $end ",
            fontFamily = rignteousFont,
            fontSize = 15.sp
        )
    }
    HorizontalDivider(thickness = 2.dp, color = Color.Black)

}

@Composable
fun PaymentDetailRow(subTotal: Float = 0.0f, shippingFee: Float = 0.0f) {
    val subTotalBigNum = BigDecimal(subTotal.toString())
    val shippingFeeBigNum = BigDecimal(shippingFee.toString())
    val totalBigNum = subTotalBigNum.add(shippingFeeBigNum)
    HorizontalDivider(thickness = 2.dp, color = Color.Black)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    ) {
        Row(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.payment_details_icon),
                contentDescription = "Payment details icon",
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(text = "Payment details", fontFamily = rignteousFont, fontSize = 15.sp)
        }
        Row {
            Text(
                text = "Merchandise subtotal:",
                fontFamily = rignteousFont,
                fontSize = 13.sp,
                color = Color(133, 126, 126)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "$$subTotalBigNum",
                    fontFamily = rignteousFont,
                    fontSize = 13.sp,
                    modifier = Modifier.align(Alignment.End),
                    color = Color(133, 126, 126)
                )
            }
        }
        Row {
            Text(
                text = "Shipping fee:",
                fontFamily = rignteousFont,
                fontSize = 13.sp,
                color = Color(133, 126, 126)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "$$shippingFeeBigNum",
                    fontFamily = rignteousFont,
                    fontSize = 13.sp,
                    modifier = Modifier.align(Alignment.End),
                    color = Color(133, 126, 126)
                )
            }
        }
        Row {
            Text(text = "Total:", fontFamily = rignteousFont, fontSize = 15.sp)
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "$$totalBigNum",
                    fontFamily = rignteousFont,
                    fontSize = 13.sp,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
    HorizontalDivider(thickness = 2.dp, color = Color.Black)
}

@Preview(showBackground = true)
@Composable
fun CartItemPaymentRowPreview() {
    CartItemPaymentRow()
}

@Preview(showBackground = true)
@Composable
fun DeliveryAddressRowPreview() {
    DeliveryAddressRow()
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DeliveryDateRowPreview() {
    DeliveryDateRow()
}

@Preview(showBackground = true)
@Composable
fun PaymentDetailRowPreview() {
    PaymentDetailRow()
}