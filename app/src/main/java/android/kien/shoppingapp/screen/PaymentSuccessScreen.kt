package android.kien.shoppingapp.screen

import android.kien.shoppingapp.library.composable.rignteousFont
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentSuccessScreen(onBackToHome: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(50.dp))
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = "Done icon",
            modifier = Modifier.size(60.dp)
        )
        Text(text = "Buy Success!", fontFamily = rignteousFont, fontSize = 30.sp)
        Row(Modifier.fillMaxHeight()) {
            Button(
                onClick = { onBackToHome() }, modifier = Modifier
                    .align(Alignment.Bottom)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Home")
            }
        }
    }
}