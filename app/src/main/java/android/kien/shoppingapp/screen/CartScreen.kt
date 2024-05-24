package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.models.CartItem
import android.kien.shoppingapp.viewmodel.CartViewModel
import android.kien.shoppingapp.viewmodel.ProductViewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    onBackClick: () -> Unit,
    onDeleteClick: (Int) -> Unit,
    onPaymentSuccess: () -> Unit,
    onQuantityChange: (Int, Int) -> Unit
) {
    val listCartItems = cartViewModel.listCartItems.value!!
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
        when (productViewModel.productList.value?.isEmpty()) {
            true -> {
                Column(
                    modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No items in cart")
                }
            }
            false -> {
                var subTotal = 0.0f
                listCartItems.forEach { cartItem ->
                    val product = productViewModel.productList.value!![cartItem.productID]
                    subTotal += product.price * cartItem.quantity
                }
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(listCartItems.size) { index ->
                        val productID = listCartItems[index].productID
                        val product = productViewModel.productList.value!![productID]
                        val quantity by remember { mutableIntStateOf(listCartItems[index].quantity) }
                        CartItemRow(
                            product = product,
                            quantity = quantity,
                            onDeleteClick = { onDeleteClick(index) },
                            onQuantityChange = { onQuantityChange(index, it) }
                        )
                    }
                    item {
                        Text(text = "SubTotal: $subTotal")
                        Spacer(modifier = Modifier.padding(10.dp))
                        Button(onClick = { onPaymentSuccess() }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Checkout")
                        }
                    }

                }

            }

            null -> {
                TODO()
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
        AsyncImage(
            model = product.imgSrc,
            modifier = Modifier.fillMaxHeight(),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentDescription = "Item Image",
            contentScale = ContentScale.FillHeight
        )
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
            mutableStateOf(false)
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun QuantityDialogPreview() {
    var quantity by remember {
        mutableIntStateOf(1)
    }
    QuantityDialog(quantity) { quantity = it }

}