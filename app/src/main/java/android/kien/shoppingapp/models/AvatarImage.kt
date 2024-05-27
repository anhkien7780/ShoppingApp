package android.kien.shoppingapp.models

import kotlinx.serialization.Serializable

@Serializable
data class AvatarImage(var url: String, val username: String)