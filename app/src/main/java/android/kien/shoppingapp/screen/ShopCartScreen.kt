package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.kien.shoppingapp.R
import android.kien.shoppingapp.data.Product
import android.kien.shoppingapp.data.allProducts
import android.kien.shoppingapp.library.composable.rignteousFont
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShopCart() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Shop Cart",
                    fontFamily = rignteousFont
                )
            },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            ShopCartItems()
        }

    }
}


@Composable
fun ShopCartItems() {
    Column(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        ShopCartItem(
            product = allProducts[0],
            amount = 1,
            onAmountChange = {}
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        ShopCartItem(
            product = allProducts[0],
            amount = 1,
            onAmountChange = {}
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        ShopCartItem(
            product = allProducts[0],
            amount = 1,
            onAmountChange = {}
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        ShopCartItem(
            product = allProducts[0],
            amount = 1,
            onAmountChange = {}
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        ShopCartItem(
            product = allProducts[0],
            amount = 1,
            onAmountChange = {}
        )

    }
}

@Composable
fun ShopCartItem(product: Product, amount: Int, onAmountChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(1.dp, color = Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Item Image",
        )
        Spacer(modifier = Modifier.padding(end = 5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("$" + product.price.toString(), fontWeight = FontWeight.Bold)
                AmountDialog(
                    amount = amount,
                    onAmountChange = onAmountChange
                )
                IconButton(onClick = { /*TODO*/ }) {
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
fun AmountDialog(amount: Int, onAmountChange: (Int) -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        var enabled by remember {
            mutableStateOf(false)
        }
        IconButton(onClick = {
            onAmountChange(amount + 1)
            enabled = true
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add product button")
        }
        Box(modifier = Modifier
            .wrapContentSize()
            .border(1.dp, Color.Black)
            .padding(10.dp)){
            Text(text = amount.toString())
        }
        IconButton(
            onClick = {
                enabled = amount > 1
                onAmountChange(amount - 1)
            },
            enabled = enabled
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
fun ShopCartPreview() {
    ShopCart()
}

@Preview(showBackground = true)
@Composable
fun ShopCartItemsPreview() {
    ShopCartItems()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ShopCartItemPreview() {
    var amount by remember {
        mutableIntStateOf(1)
    }
    Scaffold {
        ShopCartItem(
            product = allProducts[0],
            amount = amount,
            onAmountChange = { amount = it }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun AmountDialogPreview() {
    var amount by remember {
        mutableIntStateOf(1)
    }
    Scaffold {
        AmountDialog(amount) { amount = it }
    }
}