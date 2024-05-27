package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.ProductViewModel
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    onBack: () -> Unit
) {
    val listCartItems = cartViewModel.listCartItems.collectAsState()
    val subTotal = derivedStateOf {
        var total = 0.0f
        listCartItems.value.forEach { cartItem ->
            val product = productViewModel.productList.value!![cartItem.productID]
            total += product.price * cartItem.quantity
        }
        total
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "PAYMENT") }, navigationIcon = {
                IconButton(
                    onClick = onBack) {
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
            Box(modifier = Modifier.weight(1f)) {
                LazyColumn {
                    items(listCartItems.value.size) { item ->
                        val product = productViewModel.productList.value!![item]
                        CartItemPaymentRow(
                            productImageUrl = product.imgSrc,
                            productName = product.productName,
                            productPrice = product.price,
                            quantity = listCartItems.value[item].quantity
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

@Preview(showBackground = true)
@Composable
fun CartItemPaymentRowPreview() {
    CartItemPaymentRow()
}