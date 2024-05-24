package android.kien.shoppingapp.data

import android.kien.shoppingapp.R

data class Account(val id: Int, val username: String, val email: String, val password: String)

val allAccounts = mutableListOf<Account>()

data class Date(var day: Int, var month: Int, var year: Int)

data class UserInfo(
    var id: Int,
    var avatarImage: Int,
    var name: String,
    var gender: String,
    var phoneNumber: String,
    var address: String,
    var birthday: Date
)

val allUserInfo = mutableListOf<UserInfo>(
    UserInfo(
        avatarImage = R.drawable.avatar,
        name = "Flores, Juanita",
        gender = "Male",
        birthday = Date(1, 1, 2000),
        phoneNumber = "0987654321",
        address = "123 Main St",
        id = 0
    )
)

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<Int>,
    val price: Double
)

val allProducts = mutableListOf<Product>(
    Product(
        1,
        "Paris cafe with lait",
        "Tasty, sweet",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(
        2,
        "Paris cafe with lait",
        "Nice good to try",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(
        3,
        "Paris cafe with lait",
        "Oh my god, it's delicious",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(4, "Paris cafe with lait", "Flavor", listOf(R.drawable.ic_launcher_background), 5.2),
    Product(
        5,
        "Paris cafe with lait",
        "Description 1",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(
        6,
        "Paris cafe with lait",
        "Description 2",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(
        7,
        "Paris cafe with lait",
        "Description 3",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(
        8,
        "Paris cafe with lait",
        "Description 4",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(
        9,
        "Paris cafe with lait",
        "Description 5",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
    Product(
        10,
        "Paris cafe with lait",
        "Description 6",
        listOf(R.drawable.ic_launcher_background),
        5.2
    ),
)

data class CartItem(
    val product: Product,
    val quantity: Int
)

val allCartItems = mutableListOf<CartItem>()

const val seasonKeyFileLocation: String =
    "C:/Users/anhki/AndroidStudioProjects/ShoppingApp/app/src/main/java/android/kien/shoppingapp/data/userdevice/SeasonKey"
