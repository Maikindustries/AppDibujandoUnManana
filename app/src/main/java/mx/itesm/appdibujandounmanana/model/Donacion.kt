package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Clase especial, genera toString, get/set, equals,
data class DonacionData (
    @SerializedName("donationDate")
    val fecha: String,
    @SerializedName("quantity")
    val monto: String,
    @SerializedName("descripcion")
    val descripcion: String
    ) : Serializable // Pasara entre fragmentos

data class JsonDonacionData (
    @SerializedName("UserData")
    val donacion: DonacionData
) : Serializable // Pasara entre fragmentos