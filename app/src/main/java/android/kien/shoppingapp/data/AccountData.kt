package android.kien.shoppingapp.data

import java.io.File

data class AccountData(val email: String, val password: String)

val allAccounts = mutableListOf(
    AccountData("john.mclean@examplepetstore.com", "password123"),
    AccountData("admin", "admin")
)

const val seasonKeyFileLocation: String = "C:/Users/anhki/AndroidStudioProjects/ShoppingApp/app/src/main/java/android/kien/shoppingapp/data/userdevice/SeasonKey"
