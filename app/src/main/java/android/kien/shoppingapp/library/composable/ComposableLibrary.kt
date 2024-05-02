package android.kien.shoppingapp.library.composable

import android.annotation.SuppressLint
import android.kien.shoppingapp.Product
import android.kien.shoppingapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val rignteousFont = FontFamily(
    Font(R.font.righteous_regular, FontWeight.Normal)
)

val robotoMonoFont = FontFamily(
    Font(R.font.roboto_mono_italic_variable_font_weight, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_mono_variable_font_weight, FontWeight.Normal)
)

// Image
@Composable
fun ShowLogo(image: Int, w: Int = 200, h: Int = 200){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(bottom = 30.dp)
                .size(height = h.dp, width = w.dp)
        )
        Text(
            text = "Shopping App",
            fontSize = 30.sp,
            fontFamily = rignteousFont,
            fontWeight = FontWeight.Normal
        )
    }
}

// Text input
@Composable
fun EmailInput(){
    var textValue by remember{ mutableStateOf("") }
    OutlinedTextField(
        value = textValue,
        label = {
            Text(
                text = "Email"
            )
        },
        placeholder = {
            Text(
                text = "Enter your email"
            )
        },
        onValueChange = { textValue = it },
        singleLine = true,
    )
}
@Composable
fun PasswordInput(){
    var textValue by remember{ mutableStateOf("") }
    OutlinedTextField(
        value = "",
        label = {
            Text(
                text = "Password"
            )
        },
        placeholder = {
            Text(
                text = "Enter your password"
            )
        },
        onValueChange = {textValue = it},
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )
}
@Composable
fun ConfirmPasswordInput(){
    OutlinedTextField(
        value = "",
        label = {
            Text(
                text = "Confirm Password"
            )
        },
        placeholder = {
            Text(
                text = "Enter your password again"
            )
        },
        onValueChange = {},
        singleLine = true,
    )
}
@Composable
fun NameInput(){
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(
                text = "Full name",
            )
        },
        placeholder = {
              Text(
                  text = "Enter your full name"
              )
        },
        singleLine = true,

    )
}
@Composable
fun SignUpInput(){
    Column {
        EmailInput()
        PasswordInput()
    }
}


// Check box
@Composable
fun RememberPassword(){
    val rememberState = remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = rememberState.value,
            onCheckedChange = {rememberState.value = it},
        )
        Text(
            text = "Remember me"
        )
    }
}

// Button
@Composable
fun SignInButton(){
    Button(
        onClick = {},
        modifier = Modifier.size(width = 150.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(217, 217, 217))
    ) {
        Text(
            text = "Sign In",
            fontFamily = rignteousFont,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}
@Composable
fun SignUpTextButton(onNavigateToSignUp: () -> Unit){
    TextButton(
        onClick = onNavigateToSignUp
    ) {
        Text(
            text = "I don't have account yet!",
            color = Color.Black,
            fontFamily = robotoMonoFont,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
        )
    }
}
@Composable
fun SignUpButton(){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = Color(217,217,217)),
        modifier = Modifier.size(width = 150.dp, height = 50.dp)
    ) {
        Text(
            text = "Sign up",
            fontFamily = rignteousFont,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}
// SignInScreen
@Composable
fun SignInScreen(onNavigateToSignUp: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Column(horizontalAlignment = Alignment.Start){
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                ShowLogo(image = R.drawable.ic_launcher_background)
                Spacer(modifier = Modifier.height(50.dp))
                SignUpInput()
            }
            RememberPassword()
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        SignInButton()
        SignUpTextButton(onNavigateToSignUp)
    }
}
// SignUpScreen
@Composable
fun SignUpScreen(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowLogo(image = R.drawable.ic_launcher_background)
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
            NameInput()
            EmailInput()
            PasswordInput()
            ConfirmPasswordInput()
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            SignUpButton()
        }
    }
}
@Composable
fun ProductCard(productName: String, productPrice: Double, productImage: Int){
    val cardWidth = 180.dp
    val cardHeight = 330.dp
    Card (
        shape = RectangleShape,
        modifier = Modifier
            .padding(5.dp)
            .size(width = cardWidth, height = cardHeight)

    ){
        Column {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,

            ){
                Image(
                    modifier = Modifier.size(width = cardWidth, height = 250.dp),
                    painter = painterResource(id = productImage),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.FillHeight
                )
            }
            Card{
                Text(
                    text = productName
                )
            }
            Text(
                text = "\$${productPrice}",
            )

        }
    }
}

val productList = mutableListOf(
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
    Product("Cafe meo meo meo meo meo", 5.5, R.drawable.ic_launcher_background),
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyTopBar(drawerState: DrawerState, scope: CoroutineScope){
    var searchQuery by remember { mutableStateOf("") }
    CenterAlignedTopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if(isClosed) open() else close()
                            }
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
                ProvideTextStyle(
                    value = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = robotoMonoFont,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    SearchBar(
                        modifier = Modifier.width(270.dp),
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onSearch = {},
                        active = false,
                        onActiveChange = {},
                        placeholder = { Text(text = "Search something!") },
                        trailingIcon = {
                            IconButton(
                                onClick = {},
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                )
                            }
                        },
                    ) {

                    }
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun AccountDrawerSheet(accountName: String, sex: Boolean, avatarImage: Int){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(310.dp)
            .height(60.dp)
            .background(color = Color(214, 214, 214)),
    ){
        Row {
            Image(
                painter = painterResource(id = avatarImage),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(0.1.dp, color = Color.Transparent),
            )
            Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            Column {
                Text(
                    text = accountName,
                    fontFamily = rignteousFont,
                    fontSize = 18.sp,
                )

                Text(
                    text = if(!sex) "Male" else "Female",
                    fontFamily = rignteousFont,
                    fontSize = 15.sp,
                )
            }

        }
        Column (
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight()
        ) {
            TextButton(onClick = { }, contentPadding = PaddingValues()) {
                Text(
                    text = "Account setting",
                    fontFamily = rignteousFont,
                    color = Color.Blue,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun ListProductScreen(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxWidth(),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                content = {
                    Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth(0.85f)){
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Arrow Back Button")
                        }
                    }
                    AccountDrawerSheet("Flores, Juanita", false, R.drawable.avatar)
                    Spacer(modifier = Modifier.padding(horizontal = 100.dp))
                    Column (
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.86f)
                    ){
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(60.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                contentDescription = "Exit app",
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(30.dp))
                    }

                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                MyTopBar(drawerState = drawerState, scope = scope)
            },
            content = {
                    innerPadding ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(productList) {
                            item ->
                        ProductCard(
                            productName = item.productName,
                            productPrice = item.productPrice,
                            productImage = item.productImage
                        )
                    }
                }
            }
        )
    }
}





