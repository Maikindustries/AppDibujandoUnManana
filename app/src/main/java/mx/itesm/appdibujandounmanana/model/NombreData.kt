package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Clase especial, genera toString, get/set, equals,
data class NombreData (
    @SerializedName("email")
    val email: String
) : Serializable // Pasara entre fragmentos

data class JsonNombreData (
    @SerializedName("UserData")
    val nombreData: NombreData
) : Serializable // Pasara entre fragmentos