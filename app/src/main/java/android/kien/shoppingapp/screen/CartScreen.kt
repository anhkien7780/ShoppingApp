package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.library.composable.robotoMonoFont
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    onBackClick: () -> Unit,
    onPayment: () -> Unit,
) {
    val listCartItems = cartViewModel.listCartItems.collectAsState()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Shop Cart", fontFamily = rignteousFont
            )
        }, navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        })
    }) { innerPadding ->
        when (listCartItems.value.isEmpty()) {
            true -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No items in cart")
                }
            }

            false -> {
                val subTotal = derivedStateOf {
                    var total = 0.0f
                    listCartItems.value.forEach { cartID ->
                        val productIndex = cartID.productID - 1
                        val product = productViewModel.productList.value!![productIndex]
                        total += product.price * cartID.quantity
                    }
                    total
                }
                Column (modifier = Modifier.padding(innerPadding)){
                    HorizontalDivider(thickness = 2.dp, color = Color.Black)
                    Spacer(Modifier.padding(15.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        LazyColumn {
                            items(listCartItems.value.size) { index ->
                                val productID = listCartItems.value[index].productID
                                //Boi vi productID luu tren sql bat dau tu 1, ma danh sach san pham
                                //bat dau tu 0 nen productID - 1
                                val product = productViewModel.productList.value!![productID - 1]
                                var quantity by remember { mutableIntStateOf(listCartItems.value[index].quantity) }
                                CartItemRow(
                                    product = product,
                                    quantity = quantity,
                                    onDeleteClick = {
                                        cartViewModel.deleteCartItem(
                                            cartViewModel.cartID,
                                            productID
                                        )
                                    },
                                    onQuantityChange = {
                                        quantity = it
                                        cartViewModel.updateCartQuantity(
                                            cartViewModel.cartID,
                                            productID,
                                            it
                                        )
                                    }
                                )
                                Spacer(modifier = Modifier.padding(5.dp))
                            }
                            item {
                                Text(
                                    "Estimated subTotal: ${subTotal.value}",
                                    Modifier.padding(top = 10.dp, start = 5.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = robotoMonoFont,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    Row (Modifier.fillMaxWidth()){
                        Box(modifier = Modifier.align(Alignment.Bottom)) {
                            Button(
                                onClick = { onPayment() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp)
                                    .size(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(220, 170, 170)
                                )
                            ) {
                                Text(
                                    text = "CONTINUE",
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


    }
}


@Composable
fun CartItemRow(
    product: Product,
    quantity: Int,
    onDeleteClick: () -> Unit,
    onQuantityChange: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(1.dp, color = Color.Black)
    ) {
        Box(Modifier.size(70.dp)) {
            AsyncImage(
                model = product.imgSrc,
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
                text = product.productName,
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
                Text("$" + product.price.toString(), fontWeight = FontWeight.Bold)
                QuantityDialog(
                    quantity = quantity, onQuantityChange = onQuantityChange
                )
                IconButton(onClick = { onDeleteClick() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove product button"
                    )
                }
            }
        }
    }
}

@Composable
fun QuantityDialog(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        var enabled by remember {
            mutableStateOf(quantity > 1)
        }
        IconButton(onClick = {
            onQuantityChange(quantity + 1)
            enabled = true
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add product button")
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .border(1.dp, Color.Black)
                .padding(10.dp)
        ) {
            Text(text = quantity.toString())
        }
        IconButton(
            onClick = {
                enabled = quantity > 2
                onQuantityChange(quantity - 1)
            }, enabled = enabled
        ) {
            Icon(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Remove product button"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemRowPreview() {
    CartItemRow(
        product = Product(
            1,
            "product 1",
            0.4f,
            "",
            ""
        ),
        quantity = 1,
        {}, {}
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun QuantityDialogPreview() {
    var quantity by remember {
        mutableIntStateOf(1)
    }
    QuantityDialog(quantity) { quantity = it }

}