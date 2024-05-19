package android.kien.shoppingapp.library.function

import android.kien.shoppingapp.data.UserInfo
import com.google.gson.Gson
import java.io.File

fun generateSeasonKey(length: Int = 30) : String{
    val allChars = ('A'..'Z') + (0..9) + ('a'..'z')
    return (1..length)
        .map { allChars.random() }.joinToString("")
}

suspend fun loadJson(filePath: String): UserInfo {
    val file = File(filePath)
    return Gson().fromJson(file.readText(), UserInfo::class.java)
}