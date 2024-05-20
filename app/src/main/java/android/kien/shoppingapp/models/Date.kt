package android.kien.shoppingapp.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Date(val day: Int, val month: Int, val year: Int){
    @RequiresApi(Build.VERSION_CODES.O)
    fun toLocalDate(): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
        return LocalDate.parse("$day/$month/$year", formatter)
    }
}
