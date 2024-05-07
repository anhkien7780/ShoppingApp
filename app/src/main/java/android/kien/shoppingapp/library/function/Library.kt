package android.kien.shoppingapp.library.function

fun generateSeasonKey(length: Int = 30) : String{
    val allChars = ('A'..'Z') + (0..9) + ('a'..'z')
    return (1..length)
        .map { allChars.random() }.joinToString("")
}
