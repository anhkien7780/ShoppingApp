package android.kien.shoppingapp.data

import android.kien.shoppingapp.R

data class AccountData(val email: String, val password: String)

val allAccounts = mutableListOf(
    AccountData("john.mclean@examplepetstore.com", "password123"),
    AccountData("admin", "admin")
)
data class Date(var day: Int, var month: Int, var year: Int)

data class UserInfo(
    var avatarImage: Int = R.drawable.avatar,
    var name: String = "Flores, Juanita",
    var gender: String = "Male",
    var phoneNumber: String = "0777376750",
    var address: String = "2972 Westheimer Rd. Santa Ana, Illinois 85486 ",
    var birthday: Date = Date(27, 10, 2002)
)



const val seasonKeyFileLocation: String = "C:/Users/anhki/AndroidStudioProjects/ShoppingApp/app/src/main/java/android/kien/shoppingapp/data/userdevice/SeasonKey"
