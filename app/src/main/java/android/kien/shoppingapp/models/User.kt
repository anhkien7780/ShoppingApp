package android.kien.shoppingapp.models

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class User(
    val name: String,
    val birthDay: String,
    val age: Int,
    val sex: Boolean,
    val phoneNumber: String,
    val username: String
){
    @RequiresApi(Build.VERSION_CODES.O)
    fun getBirthDay(): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
        return LocalDate.parse(birthDay, formatter)
    }
}
